package com.ort.wolfmansion.domain.model.player

data class Players(
    val list: List<Player>
) {
    fun existsDifference(players: Players): Boolean {
        return list.any { player1 ->
            players.list.none { player2 -> !player1.existsDifference(player2) }
        }
    }

    fun restrictParticipation(playerId: Int?): Players {
        return Players(
            list = this.list.map { player ->
                if (player.id == playerId) player.restrictParticipation()
                else player.copy()
            }
        )
    }
}