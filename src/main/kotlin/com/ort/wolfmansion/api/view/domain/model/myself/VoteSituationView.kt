package com.ort.wolfmansion.api.view.domain.model.myself

import com.ort.wolfmansion.api.view.domain.model.village.participant.VillageParticipantView
import com.ort.wolfmansion.domain.model.myself.VoteSituation
import com.ort.wolfmansion.domain.model.village.Village

data class VoteSituationView(
    val isAvailableVote: Boolean,
    val targetList: List<VillageParticipantView>,
    val target: VillageParticipantView?
) {
    constructor(
        situation: VoteSituation,
        village: Village
    ) : this(
        isAvailableVote = situation.isAvailableVote,
        targetList = situation.targetList.map { VillageParticipantView(village, it) },
        target = situation.target?.let { VillageParticipantView(village, it) }
    )
}
