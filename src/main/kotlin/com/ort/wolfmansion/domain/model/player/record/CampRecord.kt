package com.ort.wolfmansion.domain.model.player.record

import com.ort.wolfmansion.domain.model.camp.Camp

data class CampRecord(
    val camp: Camp,
    val participateCount: Int,
    val winCount: Int,
    val winRate: Float
) {
}