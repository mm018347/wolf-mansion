package com.ort.wolfmansion.api.view.domain.model.myself

import com.ort.wolfmansion.api.view.domain.model.village.participant.VillageParticipantView
import com.ort.wolfmansion.domain.model.ability.AbilityType
import com.ort.wolfmansion.domain.model.myself.AbilitySituation
import com.ort.wolfmansion.domain.model.village.Village

data class AbilitySituationView(
    val type: AbilityType,
    val targetList: List<VillageParticipantView>,
    val target: VillageParticipantView?,
    val usable: Boolean,
    val isAvailableNoTarget: Boolean
) {
    constructor(
        situation: AbilitySituation,
        village: Village
    ) : this(
        type = situation.type,
        targetList = situation.targetList.map { VillageParticipantView(village, it) },
        target = situation.target?.let { VillageParticipantView(village, it) },
        usable = situation.usable,
        isAvailableNoTarget = situation.isAvailableNoTarget
    )
}
