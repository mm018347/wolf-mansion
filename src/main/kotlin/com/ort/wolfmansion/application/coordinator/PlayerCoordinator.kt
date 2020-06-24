package com.ort.wolfmansion.application.coordinator

import com.ort.wolfmansion.application.service.PlayerService
import com.ort.wolfmansion.application.service.VillageService
import com.ort.wolfmansion.domain.model.player.PlayerRecords
import com.ort.wolfmansion.fw.security.WolfMansionUser
import org.springframework.stereotype.Service

@Service
class PlayerCoordinator(
    private val villageService: VillageService,
    private val playerService: PlayerService
) {
    fun findPlayerRecords(user: WolfMansionUser): PlayerRecords {
        val player = playerService.findPlayer(user)
        val villages = villageService.findSolvedVillagesAsDetail(user)
        return PlayerRecords(player, villages)
    }
}