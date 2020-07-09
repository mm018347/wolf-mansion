package com.ort.wolfmansion.domain.service.ability

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.ability.AbilityType
import com.ort.wolfmansion.domain.model.charachip.Chara
import com.ort.wolfmansion.domain.model.daychange.DayChange
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.ability.VillageAbilities
import com.ort.wolfmansion.domain.model.village.ability.VillageAbility
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import org.springframework.stereotype.Service

@Service
class GuardService {

    private val abilityType = AbilityType(CDef.AbilityType.護衛)

    fun getSelectableTargetList(
        village: Village,
        participant: VillageParticipant?
    ): List<VillageParticipant> {
        participant ?: return listOf()

        // 1日目は護衛できない
        if (village.days.latestDay().day <= 1) return listOf()

        // 連続護衛可能なら自分以外の生存者全員
        // TODO 連続護衛なし
        return village.participant.list.filter {
            it.id != participant.id && it.isAlive()
        }
    }

    fun getSelectingTarget(village: Village, participant: VillageParticipant?, villageAbilities: VillageAbilities): VillageParticipant? {
        participant ?: return null

        val targetVillageParticipantId = villageAbilities.list.find {
            it.day == village.days.latestDay().day
                && it.abilityType.code == abilityType.code
                && it.myselfId == participant.id
        }?.targetId
        targetVillageParticipantId ?: return null
        return village.participant.member(targetVillageParticipantId)
    }

    fun createSetMessage(myChara: Chara, targetChara: Chara?): String {
        return "${myChara.name.name}が護衛対象を${targetChara?.name?.name ?: "なし"}に設定しました。"
    }

    fun getDefaultAbilityList(village: Village): List<VillageAbility> {
        // 進行中のみ
        if (!village.status.isProgress()) return listOf()
        // 1日目は護衛できない
        if (village.days.latestDay().day == 1) {
            return listOf()
        }

        // 最新日id
        val latestVillageDay = village.days.latestDay()
        // 生存している護衛能力持ちごとに
        return village.participant.filterAlive().list.filter {
            it.skill!!.toCdef().isHasGuardAbility
        }.mapNotNull { seer ->
            // 対象は自分以外の生存者からランダム
            // TODO 連続護衛なし
            village.participant.filterAlive()
                .findRandom { it.id != seer.id }?.let {
                    VillageAbility(
                        day = latestVillageDay.day,
                        myselfId = seer.id,
                        targetId = it.id,
                        targetFootstep = null,
                        abilityType = abilityType
                    )
                } // 自分しかいない場合null
        }
    }

    fun process(dayChange: DayChange): DayChange {
        val latestDay = dayChange.village.days.latestDay()
        var messages = dayChange.messages.copy()

        dayChange.village.participant.filterAlive().list.filter {
            it.skill!!.toCdef().isHasGuardAbility
        }.forEach { hunter ->
            dayChange.abilities.filterYesterday(dayChange.village).list.find {
                it.myselfId == hunter.id
            }?.let { ability ->
                messages = messages.add(createGuardMessage(dayChange.village, ability))
            }
        }

        return dayChange.copy(
            messages = messages
        ).setIsChange(dayChange)
    }

    fun isAvailableNoTarget(): Boolean = false

    fun isUsable(village: Village, participant: VillageParticipant): Boolean {
        // 2日目以降、生存していたら行使できる
        return village.days.latestDay().day > 1 && participant.isAlive()
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun createGuardMessage(village: Village, ability: VillageAbility): Message {
        val myChara = village.participant.member(ability.myselfId).chara
        val targetChara = village.participant.member(ability.targetId!!).chara
        val text = createGuardMessageString(myChara, targetChara)
        return Message.createPrivateSystemMessage(text, village.days.latestDay().day)
    }

    private fun createGuardMessageString(myChara: Chara, targetChara: Chara): String =
        "${myChara.name.fullName()}は、${targetChara.name.fullName()}を護衛している。"

}
