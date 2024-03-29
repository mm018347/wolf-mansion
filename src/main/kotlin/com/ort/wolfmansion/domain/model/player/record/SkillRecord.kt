package com.ort.wolfmansion.domain.model.player.record

import com.ort.wolfmansion.domain.model.player.Player
import com.ort.wolfmansion.domain.model.skill.Skill
import com.ort.wolfmansion.domain.model.village.Villages

data class SkillRecord(
    val skill: Skill,
    val participateCount: Int,
    val winCount: Int,
    val winRate: Float
) {
    constructor(
        skill: Skill,
        player: Player,
        villages: Villages
    ) : this(
        skill = skill,
        participateCount = villages.list.size,
        winCount = sumWinCount(villages, player),
        winRate = if (villages.list.isEmpty()) 0F else sumWinCount(villages, player).toFloat() / villages.list.size.toFloat()
    )

    companion object {
        private fun sumWinCount(villages: Villages, player: Player): Int {
            return villages.list.count { village ->
                village.participant.list.first { participant ->
                    participant.playerId == player.id && !participant.isGone
                }.isWin!!
            }
        }
    }
}