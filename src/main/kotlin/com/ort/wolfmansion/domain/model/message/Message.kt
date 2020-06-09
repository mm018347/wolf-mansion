package com.ort.wolfmansion.domain.model.message

data class Message(
    val fromVillageParticipantId: Int?,
    val toVillageParticipantId: Int?,
    val time: MessageTime,
    val content: MessageContent
) {
}