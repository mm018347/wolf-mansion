package com.ort.wolfmansion.domain.model.message

data class MessageContent(
    val type: MessageType,
    val num: Int?,
    val count: Int?,
    val text: String,
    val faceCode: String?
) {
}
