package com.ort.wolfmansion.domain.model.village

import java.time.LocalDateTime

data class VillageDay(
    val day: Int,
    val dayChangeDatetime: LocalDateTime
) {
    fun existsDifference(villageDay: VillageDay): Boolean {
        return day != villageDay.day || dayChangeDatetime != villageDay.dayChangeDatetime
    }
}
