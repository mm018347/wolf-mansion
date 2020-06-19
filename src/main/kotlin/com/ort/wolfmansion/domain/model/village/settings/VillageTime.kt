package com.ort.wolfmansion.domain.model.village.settings

import java.time.LocalDateTime

data class VillageTime(
    val startDatetime: LocalDateTime,
    val dayChangeIntervalSeconds: Int
) {
    fun existsDifference(time: VillageTime): Boolean {
        return startDatetime != time.startDatetime || dayChangeIntervalSeconds != time.dayChangeIntervalSeconds
    }
}
