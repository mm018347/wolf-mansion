package com.ort.wolfmansion.domain.model.myself

import com.ort.wolfmansion.domain.model.ability.AbilityType
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant

data class AbilitySituation(
    val type: AbilityType,
    val targetList: List<VillageParticipant>,
    val target: VillageParticipant?,
    val usable: Boolean,
    val isAvailableNoTarget: Boolean
) {
}
