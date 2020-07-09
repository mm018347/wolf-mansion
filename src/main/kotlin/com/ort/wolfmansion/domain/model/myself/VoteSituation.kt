package com.ort.wolfmansion.domain.model.myself

import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant

data class VoteSituation(
    val isAvailableVote: Boolean,
    val targetList: List<VillageParticipant>,
    val target: VillageParticipant?
) {
}
