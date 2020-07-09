package com.ort.wolfmansion.domain.model.myself

import com.ort.wolfmansion.domain.model.charachip.CharaFace
import com.ort.wolfmansion.domain.model.message.MessageType

data class SaySituation(
    val isAvailableSay: Boolean,
    val selectableMessageTypeList: List<SayMessageTypeSituation> = listOf(),
    val selectableFaceTypeList: List<CharaFace>,
    val defaultMessageType: MessageType?
) {
}
