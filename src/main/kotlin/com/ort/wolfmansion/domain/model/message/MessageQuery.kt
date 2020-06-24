package com.ort.wolfmansion.domain.model.message

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant

data class MessageQuery(
    val pageSize: Int?,
    val pageNum: Int?,
    val myself: VillageParticipant?,
    var messageTypeList: List<CDef.MessageType>
) {

    constructor(
        messageTypeList: List<CDef.MessageType>
    ) : this(
        pageSize = null,
        pageNum = null,
        myself = null,
        messageTypeList = messageTypeList
    )

    constructor(
        myself: VillageParticipant?,
        messageTypeList: List<CDef.MessageType>?,
        pageSize: Int?,
        pageNum: Int?,
        availableMessageTypeList: List<CDef.MessageType>
    ) : this(
        pageSize = pageSize,
        pageNum = pageNum,
        myself = myself,
        messageTypeList = listOf()
    ) {
        val requestMessageTypeList =
            if (messageTypeList.isNullOrEmpty()) CDef.MessageType.listAll()
            else messageTypeList
        val queryMessageTypeList = requestMessageTypeList.filter { availableMessageTypeList.contains(it) }
        this.messageTypeList = queryMessageTypeList
    }
}