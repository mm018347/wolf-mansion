package com.ort.wolfmansion.domain.model.player

data class Player(
    val id: Int,
    val name: String,
    val isRestrictedParticipation: Boolean
) {
}