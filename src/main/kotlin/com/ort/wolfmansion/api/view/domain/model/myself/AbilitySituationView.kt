package com.ort.wolfmansion.api.view.domain.model.myself

import com.ort.wolfmansion.api.view.domain.model.village.participant.VillageParticipantView
import com.ort.wolfmansion.domain.model.ability.AbilityType
import com.ort.wolfmansion.domain.model.myself.AbilitySituation
import com.ort.wolfmansion.domain.model.village.Village

data class AbilitySituationView(
    val type: AbilityType,
    // 誰が(人狼のみ)
    val selectableAttackerList: List<VillageParticipantView>,
    val attacker: VillageParticipantView?,
    // 対象選択
    val targetSelectAbility: Boolean,
    val selectableTargetList: List<VillageParticipantView>,
    val target: VillageParticipantView?,
    // 足音選択
    val footstepSelectAbility: Boolean,
    val selectableFootstepList: List<String>,
    val footstep: String?,

    val usable: Boolean,
    val availableNoTarget: Boolean
) {
    constructor(
        situation: AbilitySituation,
        village: Village
    ) : this(
        type = situation.type,
        selectableAttackerList = situation.selectableAttackerList.map { VillageParticipantView(village, it) },
        attacker = situation.attacker?.let { VillageParticipantView(village, it) },
        targetSelectAbility = situation.targetSelectAbility,
        selectableTargetList = situation.selectableTargetList.map { VillageParticipantView(village, it) },
        target = situation.target?.let { VillageParticipantView(village, it) },
        footstepSelectAbility = situation.footstepSelectAbility,
        selectableFootstepList = situation.selectableFootstepList,
        footstep = situation.footstep,
        usable = situation.usable,
        availableNoTarget = situation.availableNoTarget
    )
}
