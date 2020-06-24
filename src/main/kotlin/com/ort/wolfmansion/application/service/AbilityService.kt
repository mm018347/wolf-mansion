package com.ort.wolfmansion.application.service

import com.ort.wolfmansion.domain.model.village.ability.VillageAbilities
import com.ort.wolfmansion.domain.model.village.ability.VillageAbility
import com.ort.wolfmansion.infrastructure.datasource.ability.AbilityDataSource
import org.springframework.stereotype.Service

@Service
class AbilityService(
    val abilityDataSource: AbilityDataSource
) {

    fun findVillageAbilities(
        villageId: Int
    ): VillageAbilities = abilityDataSource.findAbilities(villageId)

    fun updateAbility(
        villageId: Int,
        villageAbility: VillageAbility
    ) = abilityDataSource.updateAbility(villageId, villageAbility)

    fun updateDifference(
        villageId: Int,
        before: VillageAbilities,
        after: VillageAbilities
    ) = abilityDataSource.updateDifference(villageId, before, after)
}