package com.ort.wolfmansion.domain.model.message

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.fw.exception.WolfMansionBusinessException

data class MessageContent(
    val type: MessageType,
    val num: Int?,
    val text: String,
    val convertDisable: Boolean,
    val faceCode: String?
) {

    constructor(
        messageType: CDef.MessageType,
        text: String,
        convertDisable: Boolean,
        faceType: CDef.FaceType?
    ) : this(
        type = MessageType(messageType),
        num = null,
        text = removeSurrogate(text.trim()),
        convertDisable = convertDisable,
        faceCode = faceType?.code()
    )

    companion object {

        const val lengthMax: Int = 400
        const val lineMax: Int = 20

        /**
         * 絵文字を除く文字列を返す
         * @param text
         * @return 4byte文字を除いた文字列
         */
        private fun removeSurrogate(text: String): String {
            return text.chunked(1).filter { c ->
                !c.matches("[\\uD800-\\uDFFF]".toRegex())
            }.joinToString(separator = "")
        }
    }

    fun assertMessageLength(maxLength: Int) {
        // 行数
        if (text.replace("\r\n", "\n").split("\n").size > lineMax) throw WolfMansionBusinessException("行数オーバーです")
        // 文字数
        if (text.isEmpty()) throw WolfMansionBusinessException("発言内容がありません")
        // 改行は文字数としてカウントしない
        val length = text.replace("\r\n", "").replace("\n", "").length
        if (length <= 0) throw WolfMansionBusinessException("発言内容がありません") // 改行のみもNG
        if (maxLength < length) throw WolfMansionBusinessException("文字数オーバーです")
    }

}
