package com.ort.wolfmansion.api.view.domain.model.village.participant

import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipants

data class VillageParticipantsView(
    val count: Int,
    val list: List<VillageParticipantView>
) {
    constructor(
        village: Village,
        participants: VillageParticipants
    ) : this(
        count = participants.count,
        list = participants.list.map {
            VillageParticipantView(
                village = village,
                participant = it
            )
        }
    )
}