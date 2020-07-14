package com.ort.wolfmansion.api.view.domain.model.myself

import com.ort.wolfmansion.domain.model.myself.CommitSituation
import com.ort.wolfmansion.domain.model.myself.CreatorSituation
import com.ort.wolfmansion.domain.model.myself.ParticipateSituation
import com.ort.wolfmansion.domain.model.myself.SituationAsParticipant
import com.ort.wolfmansion.domain.model.myself.SkillRequestSituation
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant

data class SituationAsParticipantView(
    val myself: VillageParticipant?,
    val participate: ParticipateSituation,
    val skillRequest: SkillRequestSituation,
    val commit: CommitSituation,
    val say: SaySituationView,
    val ability: AbilitySituationsView,
    val vote: VoteSituationView,
    val creator: CreatorSituation,
    val viewableSpoilerContent: Boolean
) {
    constructor(
        situation: SituationAsParticipant,
        village: Village
    ) : this(
        myself = situation.myself,
        participate = situation.participate,
        skillRequest = situation.skillRequest,
        commit = situation.commit,
        say = SaySituationView(situation.say, village),
        ability = AbilitySituationsView(situation.ability, village),
        vote = VoteSituationView(situation.vote, village),
        creator = situation.creator,
        viewableSpoilerContent = situation.viewableSpoilerContent
    )
}