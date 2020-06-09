package com.ort.wolfmansion.domain.model.player.record

import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.VillageParticipant

data class ParticipateVillage(
    val village: Village,
    val participant: VillageParticipant
) {
}