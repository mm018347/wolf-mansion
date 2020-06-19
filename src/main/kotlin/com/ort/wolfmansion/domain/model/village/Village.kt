package com.ort.wolfmansion.domain.model.village

import com.ort.wolfmansion.domain.model.camp.Camp

data class Village(
    val id: Int,
    val name: String,
    val creatorPlayerName: String,
    val status: VillageStatus,
    val winCamp: Camp?,
    val setting: VillageSettings,
    val participant: VillageParticipants,
    val spectator: VillageParticipants,
    val day: VillageDays
) {
}