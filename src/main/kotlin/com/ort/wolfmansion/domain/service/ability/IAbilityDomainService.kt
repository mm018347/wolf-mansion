package com.ort.wolfmansion.domain.service.ability

import com.ort.wolfmansion.domain.model.ability.AbilityType
import com.ort.wolfmansion.domain.model.daychange.DayChange
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.ability.VillageAbilities
import com.ort.wolfmansion.domain.model.village.ability.VillageAbility
import com.ort.wolfmansion.domain.model.village.footstep.VillageFootsteps
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant

interface IAbilityDomainService {

    fun getAbilityType(): AbilityType

    fun getSelectableAttackerList(
        village: Village,
        villageAbilities: VillageAbilities
    ): List<VillageParticipant> {

    }

    fun getSelectableTargetList(
        village: Village,
        participant: VillageParticipant?,
        villageAbilities: VillageAbilities
    ): List<VillageParticipant>

    fun getSelectingTarget(
        village: Village,
        participant: VillageParticipant?,
        villageAbilities: VillageAbilities
    ): VillageParticipant?

    fun getSelectableFootstepList(
        village: Village,
        participant: VillageParticipant?,
        footsteps: VillageFootsteps
    ): List<String>

    fun getSelectingFootstep(
        village: Village,
        participant: VillageParticipant?,
        villageAbilities: VillageAbilities
    ): String?

    fun createSetMessage(myself: VillageParticipant, target: VillageParticipant?, footstep: String?): String

    fun getDefaultAbilityList(village: Village, villageAbilities: VillageAbilities): List<VillageAbility>

    fun processDayChangeAction(dayChange: DayChange): DayChange

    fun isAvailableNoTarget(): Boolean

    fun isUsable(village: Village, participant: VillageParticipant): Boolean
}