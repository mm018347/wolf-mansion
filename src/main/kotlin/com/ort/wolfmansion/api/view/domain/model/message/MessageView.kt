package com.ort.wolfmansion.api.view.domain.model.message

import com.ort.wolfmansion.api.view.domain.model.village.participant.VillageParticipantView
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.message.MessageTime
import com.ort.wolfmansion.domain.model.village.Village

data class MessageView(
    val from: VillageParticipantView?,
    val to: VillageParticipantView?,
    val time: MessageTime,
    val content: MessageContentView
) {
    constructor(
        message: Message,
        village: Village
    ) : this(
        from = message.fromParticipantId?.let {
            VillageParticipantView(
                village = village,
                participant = village.findPersonByParticipantId(it)!!
            )
        },
        to = message.toParticipantId?.let {
            VillageParticipantView(
                village = village,
                participant = village.findPersonByParticipantId(it)!!
            )
        },
        time = message.time,
        content = MessageContentView(village, message.content)
    )
}