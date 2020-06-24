package com.ort.wolfmansion.domain.model.daychange

import com.ort.wolfmansion.domain.model.message.Messages
import com.ort.wolfmansion.domain.model.player.Players
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.ability.VillageAbilities
import com.ort.wolfmansion.domain.model.village.footstep.VillageFootsteps
import com.ort.wolfmansion.domain.model.village.vote.VillageVotes

data class DayChange(
    val isChanged: Boolean,
    val village: Village,
    val messages: Messages,
    val votes: VillageVotes,
    val abilities: VillageAbilities,
    val footsteps: VillageFootsteps,
    val players: Players
) {

    constructor(
        village: Village,
        votes: VillageVotes,
        abilities: VillageAbilities,
        footsteps: VillageFootsteps,
        players: Players
    ) : this(
        isChanged = false,
        village = village,
        messages = Messages(listOf()),
        votes = votes,
        abilities = abilities,
        footsteps = footsteps,
        players = players
    )

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    fun setIsChange(before: DayChange): DayChange {
        return if (isChanged) this
        else this.copy(isChanged = existsDifference(before))
    }

    private fun existsDifference(before: DayChange): Boolean {
        // village
        if (village.existsDifference(before.village)) return true
        // message
        if (messages.existsDifference(before.messages)) return true
        // votes
        if (votes.existsDifference(before.votes)) return true
        // abilities
        if (abilities.existsDifference(before.abilities)) return true
        // footsteps
        if (footsteps.existsDifference(before.footsteps)) return true
        // players
        return players.existsDifference(before.players)
    }
}