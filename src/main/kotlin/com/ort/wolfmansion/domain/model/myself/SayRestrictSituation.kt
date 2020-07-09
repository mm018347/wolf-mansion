package com.ort.wolfmansion.domain.model.myself

data class SayRestrictSituation(
    val isRestricted: Boolean,
    val maxCount: Int?,
    val remainingCount: Int?,
    val maxLength: Int,
    val maxLine: Int
) {

}
