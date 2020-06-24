package com.ort.wolfmansion.domain.model.message

import com.ort.dbflute.allcommon.CDef

data class MessageType(
    val code: String,
    val name: String
) {

    constructor(
        cdef: CDef.MessageType
    ) : this(
        code = cdef.code(),
        name = cdef.alias()
    )

    companion object {
        val personalPrivateAbilityList = listOf(
            CDef.MessageType.白黒占い結果,
            CDef.MessageType.役職占い結果,
            CDef.MessageType.足音調査結果
        )

        val requiredCountTypeList: List<CDef.MessageType> = listOf(
            CDef.MessageType.通常発言,
            CDef.MessageType.死者の呻き,
            CDef.MessageType.独り言,
            CDef.MessageType.人狼の囁き,
            CDef.MessageType.見学発言,
            CDef.MessageType.共鳴発言
        )
    }

    fun toCdef(): CDef.MessageType = CDef.MessageType.codeOf(this.code)

    fun shouldSetCount(): Boolean = requiredCountTypeList.contains(this.toCdef())
}