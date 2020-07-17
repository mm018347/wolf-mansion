package com.ort.wolfmansion.domain.service.ability

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.ability.AbilityType
import com.ort.wolfmansion.domain.model.daychange.DayChange
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.ability.VillageAbilities
import com.ort.wolfmansion.domain.model.village.ability.VillageAbility
import com.ort.wolfmansion.domain.model.village.footstep.VillageFootstep
import com.ort.wolfmansion.domain.model.village.footstep.VillageFootsteps
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import org.springframework.stereotype.Service

@Service
class InvestigationService : IAbilityDomainService {

    override fun getAbilityType(): AbilityType = AbilityType(CDef.AbilityType.捜査)

    override fun getSelectableTargetList(
        village: Village,
        participant: VillageParticipant?,
        villageAbilities: VillageAbilities
    ): List<VillageParticipant> {
        return listOf() // 対象選択型でないので不要
    }

    override fun getSelectingTarget(
        village: Village,
        participant: VillageParticipant?,
        villageAbilities: VillageAbilities
    ): VillageParticipant? {
        return null // 対象選択型でないので不要
    }

    override fun getSelectableFootstepList(
        village: Village,
        participant: VillageParticipant?,
        footsteps: VillageFootsteps
    ): List<String> {
        if (village.days.latestDay().day < 2) return listOf()
        return footsteps.convertToDayDispFootsteps(
            participants = village.participant,
            day = village.days.yesterday().day
        ).list.map { it.footsteps!! }.distinct()
    }

    override fun getSelectingFootstep(
        village: Village,
        participant: VillageParticipant?,
        villageAbilities: VillageAbilities
    ): String? {
        participant ?: return null
        if (village.days.latestDay().day < 2) return null
        return villageAbilities.filterByDay(village.days.latestDay().day).list.firstOrNull {
            it.abilityType.code == getAbilityType().code && it.myselfId == participant.id
        }?.targetFootstep
    }

    override fun createSetMessage(
        myself: VillageParticipant,
        target: VillageParticipant?,
        footstep: String?
    ): String {
        return "${myself.name()}が調査する部屋を${footstep ?: "なし"}に設定しました。"
    }

    override fun getDefaultAbilityList(
        village: Village,
        villageAbilities: VillageAbilities
    ): List<VillageAbility> {
        // 最新日
        val latestDay = village.days.latestDay()
        // 進行中かつ2日目以降のみ
        if (!village.status.isProgress() || latestDay.day < 2) return listOf()
        // 生存している占い能力持ちごとに
        return village.participant.filterAlive().list.filter {
            it.skill!!.toCdef().isHasGuardAbility
        }.mapNotNull { seer ->
            // 対象は自分以外の生存者からランダム
            village.participant
                .filterAlive()
                .findRandom { it.id != seer.id }?.let {
                    VillageAbility(
                        day = latestDay.day,
                        myselfId = seer.id,
                        targetId = it.id,
                        targetFootstep = null,
                        abilityType = getAbilityType()
                    )
                }
        }
    }

    override fun processDayChangeAction(
        dayChange: DayChange
    ): DayChange {
        var messages = dayChange.messages.copy()
        val village = dayChange.village.copy()

        dayChange.village.participant.filterAlive().list.filter {
            it.skill!!.toCdef().isHasInvestigateAbility
        }.forEach { detective ->
            dayChange.abilities.filterYesterday(village).list.find {
                it.myselfId == detective.id && it.abilityType.code == getAbilityType().code
            }?.let { ability ->
                dayChange.footsteps
                    .convertToDayDispFootsteps(village.participant, village.days.latestDay().day - 2).list
                    .shuffled()
                    .firstOrNull { it.footsteps == ability.targetFootstep }
                    ?.let {
                        messages = messages.add(createInvestigateMessage(dayChange.village, it, detective))
                    }
            }
        }

        return dayChange.copy(
            messages = messages
        ).setIsChange(dayChange)
    }

    override fun isAvailableNoTarget(): Boolean = true

    override fun isUsable(village: Village, participant: VillageParticipant): Boolean = participant.isAlive() // 生存していたら行使できる

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun createInvestigateMessage(village: Village, villageFootstep: VillageFootstep, detective: VillageParticipant): Message {
        val target = village.participant.member(villageFootstep.myselfId)
        val text = createInvestigateMessageString(detective, target, villageFootstep.footsteps)
        return Message.createInvastigatePrivateMessage(text, village.days.latestDay().day, detective)
    }

    private fun createInvestigateMessageString(detective: VillageParticipant, target: VillageParticipant, footsteps: String?): String {
        return "${detective.name()}は、昨日響いた足音${footsteps}について調査した。\n${footsteps}の足音を響かせたのは${target.skill!!.name}のようだ。"
    }
}
