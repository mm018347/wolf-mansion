package com.ort.wolfmansion.domain.model.player.record

import com.ort.wolfmansion.domain.model.player.Player
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant

data class ParticipateVillage(
    val village: Village,
    val participant: VillageParticipant
) {
    constructor(
        player: Player,
        village: Village
    ) : this(
        village = village,
        participant = village.participant.list.first { !it.isGone && it.playerId == player.id }
    )
}