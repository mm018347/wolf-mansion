package com.ort.wolfmansion.domain.service.ability

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.ability.AbilityType
import com.ort.wolfmansion.domain.model.charachip.Chara
import com.ort.wolfmansion.domain.model.daychange.DayChange
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.ability.VillageAbilities
import com.ort.wolfmansion.domain.model.village.ability.VillageAbility
import com.ort.wolfmansion.domain.model.village.footstep.VillageFootsteps
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import org.springframework.stereotype.Service

@Service
class GuardService : IAbilityDomainService {

    override fun getAbilityType(): AbilityType = AbilityType(CDef.AbilityType.護衛)

    override fun getSelectableTargetList(
        village: Village,
        participant: VillageParticipant?,
        villageAbilities: VillageAbilities
    ): List<VillageParticipant> {
        participant ?: return listOf()

        // 1日目は護衛できない
        if (village.days.latestDay().day <= 1) return listOf()

        val candidateList = village.participant
            .filterAlive().list
            .filterNot { it.id == participant.id }
        return if (village.setting.rules.availableGuardSameTarget) {
            // 連続護衛可能なら自分以外の生存者全員
            candidateList
        } else {
            // 連続護衛不可なら自分と昨日護衛した人以外
            val yesterdayTargetId = villageAbilities
                .filterYesterday(village)
                .filterByAbility(getAbilityType()).list
                .firstOrNull { it.myselfId == participant.id }
                ?.targetId
            candidateList.filterNot { it.id == yesterdayTargetId }
        }
    }

    override fun getSelectingTarget(village: Village, participant: VillageParticipant?, villageAbilities: VillageAbilities): VillageParticipant? {
        participant ?: return null

        val targetVillageParticipantId = villageAbilities.list.find {
            it.day == village.days.latestDay().day
                && it.abilityType.code == getAbilityType().code
                && it.myselfId == participant.id
        }?.targetId
        targetVillageParticipantId ?: return null
        return village.participant.member(targetVillageParticipantId)
    }

    override fun getSelectableFootstepList(village: Village, participant: VillageParticipant?, footsteps: VillageFootsteps): List<String> {
        return listOf() // 足音選択型でないので不要
    }

    override fun getSelectingFootstep(village: Village, participant: VillageParticipant?, villageAbilities: VillageAbilities): String? {
        return null // 足音選択型でないので不要
    }

    override fun createSetMessage(
        myself: VillageParticipant,
        target: VillageParticipant?,
        footstep: String?
    ): String {
        return "${myself.name()}が護衛対象を${checkNotNull(target?.name())}に、通過する部屋を${footstep ?: "なし"}に設定しました。"
    }

    override fun getDefaultAbilityList(village: Village, villageAbilities: VillageAbilities): List<VillageAbility> {
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
        }.mapNotNull { hunter ->
            // 対象は自分以外の生存者からランダム
            this.getSelectableTargetList(village, hunter, villageAbilities).shuffled().firstOrNull()?.let {
                VillageAbility(
                    day = latestVillageDay.day,
                    myselfId = hunter.id,
                    targetId = it.id,
                    targetFootstep = null,
                    abilityType = getAbilityType()
                )
            } // 自分しかいない場合null
        }
    }

    override fun processDayChangeAction(dayChange: DayChange): DayChange {
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

    override fun isAvailableNoTarget(): Boolean = false

    override fun isUsable(village: Village, participant: VillageParticipant): Boolean {
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
