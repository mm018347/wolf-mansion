package com.ort.wolfmansion.domain.model.dead

import com.ort.wolfmansion.domain.model.village.VillageDay

data class Dead(
    val code: String,
    val reason: String,
    val villageDay: VillageDay
) {
}