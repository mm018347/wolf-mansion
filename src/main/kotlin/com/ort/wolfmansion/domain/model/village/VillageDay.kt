package com.ort.wolfmansion.domain.model.village

import java.time.LocalDateTime

data class VillageDay(
    val id: Int,
    val day: Int,
    val dayChangeDatetime: LocalDateTime
) {

}
