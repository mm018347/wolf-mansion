package com.ort.wolfmansion.domain.model.player.record

import com.ort.wolfmansion.domain.model.skill.Skill

data class SkillRecord(
    val skill: Skill,
    val participateCount: Int,
    val winCount: Int,
    val winRate: Float
) {
}