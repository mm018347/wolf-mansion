package com.ort.wolfmansion.domain.model.village.settings

import java.time.LocalDateTime

data class VillageTime(
    val startDatetime: LocalDateTime,
    val dayChangeIntervalSeconds: Int
) {

}
