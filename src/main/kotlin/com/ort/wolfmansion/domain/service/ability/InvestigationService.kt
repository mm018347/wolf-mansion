package com.ort.wolfmansion.domain.service.ability

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.ability.AbilityType
import com.ort.wolfmansion.domain.model.daychange.DayChange
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.ability.VillageAbilities
import com.ort.wolfmansion.domain.model.village.ability.VillageAbility
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isAvailableNoTarget(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isUsable(
        village: Village,
        participant: VillageParticipant
    ): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
