package com.ort.wolfmansion.domain.model.daychange

import com.ort.wolfmansion.domain.model.message.Messages
import com.ort.wolfmansion.domain.model.player.Players
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.ability.VillageAbilities
import com.ort.wolfmansion.domain.model.village.vote.VillageVotes

data class DayChange(
    val isChanged: Boolean,
    val village: Village,
    val messages: Messages,
    val votes: VillageVotes,
    val abilities: VillageAbilities,
    val players: Players
) {
}