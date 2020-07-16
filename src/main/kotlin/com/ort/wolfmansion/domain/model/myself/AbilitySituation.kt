package com.ort.wolfmansion.domain.model.myself

import com.ort.wolfmansion.domain.model.ability.AbilityType
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant

data class AbilitySituation(
    val type: AbilityType,
    // 誰が(人狼のみ)
    val selectableAttackerList: List<VillageParticipant>,
    val attacker: VillageParticipant?,
    // 対象選択
    val targetSelectAbility: Boolean,
    val selectableTargetList: List<VillageParticipant>,
    val target: VillageParticipant?,
    // 足音選択
    val footstepSelectAbility: Boolean,
    val selectableFootstepList: List<String>,
    val footstep: String?,

    val usable: Boolean,
    val availableNoTarget: Boolean
) {
}
