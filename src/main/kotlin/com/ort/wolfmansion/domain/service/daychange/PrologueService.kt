package com.ort.wolfmansion.domain.service.daychange

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.daychange.DayChange
import com.ort.wolfmansion.domain.model.message.Messages
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.service.ability.AbilityDomainService
import com.ort.wolfmansion.domain.service.skill.SkillAssignService
import com.ort.wolfmansion.domain.service.village.participate.LeaveService
import com.ort.wolfmansion.util.WolfMansionDateUtil
import org.springframework.stereotype.Service

@Service
class PrologueService(
    private val leaveService: LeaveService,
    private val abilityDomainService: AbilityDomainService,
    private val skillAssignService: SkillAssignService
) {

    fun leaveParticipantIfNeeded(
        dayChange: DayChange,
        todayMessages: Messages
    ): DayChange {
        // 24時間以内の発言
        val recentMessageList =
            todayMessages.list.filter { it.time.datetime.isAfter(WolfMansionDateUtil.currentLocalDateTime().minusDays(1L)) }
        // 24時間以内に発言していなかったら退村
        var village = dayChange.village.copy()
        var messages = dayChange.messages.copy()
        dayChange.village.notDummyParticipant().list.forEach { member ->
            if (recentMessageList.none { message -> message.fromParticipantId!! == member.id }) {
                village = village.leaveParticipant(member.id)
                messages = messages.add(leaveService.createLeaveMessage(village, member.chara))
            }
        }
        return dayChange.copy(
            village = village,
            messages = messages
        ).setIsChange(dayChange)
    }

    fun addDayIfNeeded(
        dayChange: DayChange
    ): DayChange {
        // 開始時刻になっていない場合は何もしない
        if (!shouldForward(dayChange.village)) return dayChange
        // 開始時刻になっているが参加者数が不足している場合は廃村にする
        if (isNotEnoughMemberCount(dayChange.village)) return cancelVillage(dayChange).setIsChange(dayChange)
        // 新しい日付追加
        return dayChange.copy(village = dayChange.village.addNewDay()).setIsChange(dayChange)
    }

    fun dayChange(
        dayChange: DayChange
    ): DayChange {
        // 開始メッセージ追加
        var messages = dayChange.messages.add(dayChange.village.createVillageDay1Message())
        // 役職割り当て
        val villageParticipants = skillAssignService.assign(dayChange.village)
        var village = dayChange.village.assignSkill(villageParticipants)
        // 役職構成メッセージ追加
        messages = messages.add(village.createOrganizationMessage())
        // ステータス変更
        village = village.changeStatus(CDef.VillageStatus.進行中)
        // デフォルト能力行使指定
        val abilities = dayChange.abilities.add(abilityDomainService.createDefaultAbilities(village))
        // ダミーキャラ発言
        village.createDummyCharaFirstDayMessage()?.let { messages = messages.add(it) }

        return dayChange.copy(
            village = village,
            messages = messages,
            abilities = abilities
        ).setIsChange(dayChange)
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    // 日付を進める必要があるか
    private fun shouldForward(village: Village): Boolean =
        !WolfMansionDateUtil.currentLocalDateTime().isBefore(village.days.latestDay().dayChangeDatetime)

    // 参加者数が不足している場合に廃村にする
    private fun cancelVillage(dayChange: DayChange): DayChange {
        return dayChange.copy(
            village = dayChange.village.changeStatus(CDef.VillageStatus.廃村),
            messages = dayChange.messages.add(dayChange.village.createCancelVillageMessage())
        )
    }

    private fun isNotEnoughMemberCount(village: Village) =
        village.participant.list.count { !it.isGone } < village.setting.capacity.min
}