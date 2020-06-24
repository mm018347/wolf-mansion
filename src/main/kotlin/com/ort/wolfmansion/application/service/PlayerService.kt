package com.ort.wolfmansion.application.service

import com.ort.wolfmansion.domain.model.player.Player
import com.ort.wolfmansion.domain.model.player.Players
import com.ort.wolfmansion.fw.security.WolfMansionUser
import com.ort.wolfmansion.infrastructure.datasource.player.PlayerDataSource
import org.springframework.stereotype.Service

@Service
class PlayerService(
    private val playerDataSource: PlayerDataSource
) {

    fun findPlayer(id: Int): Player = playerDataSource.findPlayer(id)

    fun findPlayer(user: WolfMansionUser): Player = playerDataSource.findPlayer(user.username)

    fun findPlayers(villageId: Int): Players = playerDataSource.findPlayers(villageId)

    fun findPlayers(playerIdList: List<Int>): Players = playerDataSource.findPlayers(playerIdList)

    fun updatePassword(user: WolfMansionUser, password: String) = playerDataSource.update(user.username, password)

    fun updateDifference(before: Players, after: Players) = playerDataSource.updateDifference(before, after)
}