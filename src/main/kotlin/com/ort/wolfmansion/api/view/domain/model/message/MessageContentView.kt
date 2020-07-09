package com.ort.wolfmansion.api.view.domain.model.message

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.message.MessageContent
import com.ort.wolfmansion.domain.model.message.MessageType
import com.ort.wolfmansion.domain.model.village.Village

data class MessageContentView(
    val type: MessageType,
    val num: Int?,
    val text: String,
    val convertDisable: Boolean,
    val faceCode: String?
) {
    constructor(
        village: Village,
        messageContent: MessageContent
    ) : this(
        type = messageContent.type,
        num = if (messageContent.type.toCdef() == CDef.MessageType.独り言 && !village.status.isSolved()) null
        else messageContent.num,
        text = messageContent.text,
        convertDisable = messageContent.convertDisable,
        faceCode = messageContent.faceCode
    )
}