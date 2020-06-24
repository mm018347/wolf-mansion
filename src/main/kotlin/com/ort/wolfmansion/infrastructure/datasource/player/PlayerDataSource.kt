package com.ort.wolfmansion.infrastructure.datasource.player

import com.ort.dbflute.exbhv.PlayerBhv
import com.ort.dbflute.exbhv.VillageBhv
import com.ort.dbflute.exentity.Player
import com.ort.wolfmansion.domain.model.player.Players
import org.springframework.stereotype.Repository

@Repository
class PlayerDataSource(
    private val playerBhv: PlayerBhv,
    private val villageBhv: VillageBhv
) {

    fun findPlayer(id: Int): com.ort.wolfmansion.domain.model.player.Player {
        val player = playerBhv.selectEntityWithDeletedCheck {
            it.query().setPlayerId_Equal(id)
        }
        playerBhv.load(player) {
            it.loadVillagePlayer { vpCB ->
                vpCB.setupSelect_Village()
                vpCB.query().queryVillage().arrangeNotSolvedStatus()
                vpCB.query().setIsGone_Equal(false) // participant village
            }
        }
        val notSolvedCreateVillageIdList = selectNotSolvedCreateVillageIdList(player)
        return convertPlayerToPlayer(player, notSolvedCreateVillageIdList)
    }

    fun findPlayer(playerName: String): com.ort.wolfmansion.domain.model.player.Player {
        val player = playerBhv.selectEntityWithDeletedCheck {
            it.query().setPlayerName_Equal(playerName)
        }
        playerBhv.load(player) {
            it.loadVillagePlayer { vpCB ->
                vpCB.setupSelect_Village()
                vpCB.query().queryVillage().arrangeNotSolvedStatus()
                vpCB.query().setIsGone_Equal(false) // participant village
            }
        }
        val notSolvedCreateVillageIdList = selectNotSolvedCreateVillageIdList(player)
        return convertPlayerToPlayer(player, notSolvedCreateVillageIdList)
    }

    fun findPlayers(villageId: Int): Players {
        val playerList = playerBhv.selectList {
            it.query().existsVillagePlayer {
                it.query().setVillageId_Equal(villageId)
            }
        }
        return Players(list = playerList.map { convertPlayerToSimplePlayer(it) })
    }

    fun findPlayers(playerIdList: List<Int>): Players {
        if (playerIdList.isEmpty()) return Players(listOf())
        val playerList = playerBhv.selectList {
            it.query().existsVillagePlayer {
                it.query().setPlayerId_InScope(playerIdList)
            }
        }
        return Players(list = playerList.map { convertPlayerToSimplePlayer(it) })
    }

    fun update(playerName: String, password: String) {
        val player = Player()
        player.uniqueBy(playerName)
        player.playerPassword = password
        playerBhv.update(player)
    }

    fun updateDifference(before: Players, after: Players) {
        // player
        after.list.forEach {
            val beforePlayer = before.list.first { bP -> bP.id == it.id }
            if (it.existsDifference(beforePlayer)) {
                val player = Player()
                player.playerId = it.id
                player.isRestrictedParticipation = it.isRestrictedParticipation
                playerBhv.update(player)
            }
        }
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun selectNotSolvedCreateVillageIdList(player: Player): List<Int> {
        return villageBhv.selectList {
            it.query().setCreatePlayerName_Equal(player.playerName)
            it.query().arrangeNotSolvedStatus()
        }.map { it.villageId }
    }

    // ===================================================================================
    //                                                                             Mapping
    //                                                                             =======
    private fun convertPlayerToPlayer(
        player: Player,
        notSolvedCreateVillageIdList: List<Int>
    ): com.ort.wolfmansion.domain.model.player.Player {
        return com.ort.wolfmansion.domain.model.player.Player(
            id = player.playerId,
            name = player.playerName,
            isRestrictedParticipation = player.isRestrictedParticipation,
            participatingNotSolvedVillageIdList = player.villagePlayerList.map { it.villageId },
            createNotSolvedVillageIdList = notSolvedCreateVillageIdList
        )
    }

    private fun convertPlayerToSimplePlayer(player: Player): com.ort.wolfmansion.domain.model.player.Player {
        return com.ort.wolfmansion.domain.model.player.Player(
            id = player.playerId,
            name = player.playerName,
            isRestrictedParticipation = player.isRestrictedParticipation
        )
    }
}