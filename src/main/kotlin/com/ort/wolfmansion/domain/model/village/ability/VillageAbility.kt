package com.ort.wolfmansion.domain.model.village.ability

import com.ort.wolfmansion.domain.model.ability.AbilityType

data class VillageAbility(
    val day: Int,
    val myselfId: Int,
    val targetId: Int?,
    val targetFootstep: String?,
    val abilityType: AbilityType
) {
}