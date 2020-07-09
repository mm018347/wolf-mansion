package com.ort.wolfmansion.domain.model.myself

import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant

data class SituationAsParticipant(
    val myself: VillageParticipant?,
    val participate: ParticipateSituation,
    val skillRequest: SkillRequestSituation,
    val commit: CommitSituation,
    val say: SaySituation,
    val ability: AbilitySituations,
    val vote: VoteSituation,
    val creator: CreatorSituation
) {
}