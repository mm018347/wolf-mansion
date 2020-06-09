package com.ort.wolfmansion.domain.model.village.vote

data class VillageVote(
    val villageDayId: Int,
    val myselfId: Int,
    val targetId: Int
) {
}