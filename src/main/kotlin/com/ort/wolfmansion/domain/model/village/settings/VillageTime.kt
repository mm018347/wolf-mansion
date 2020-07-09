package com.ort.wolfmansion.domain.model.village.settings

import java.time.LocalDateTime

data class VillageTime(
    val startDatetime: LocalDateTime,
    val dayChangeIntervalSeconds: Int
) {

    companion object {
        const val dayChangeIntervalHours_min: Int = 0
        const val dayChangeIntervalHours_max: Int = 48


    }

    fun existsDifference(time: VillageTime): Boolean {
        return startDatetime != time.startDatetime || dayChangeIntervalSeconds != time.dayChangeIntervalSeconds
    }
}
