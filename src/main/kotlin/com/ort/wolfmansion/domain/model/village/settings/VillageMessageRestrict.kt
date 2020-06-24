package com.ort.wolfmansion.domain.model.village.settings

import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.message.MessageContent
import com.ort.wolfmansion.domain.model.message.MessageType
import com.ort.wolfmansion.domain.model.skill.Skill
import com.ort.wolfmansion.domain.model.village.VillageStatus
import com.ort.wolfmansion.fw.exception.WolfMansionBusinessException

data class VillageMessageRestrict(
    val skill: Skill,
    val messageType: MessageType,
    val count: Int,
    val length: Int
) {

    fun assertSay(messageContent: MessageContent, status: VillageStatus, latestDayMessageList: List<Message>) {
        // 回数
        if (remainingCount(status, latestDayMessageList) <= 0) throw WolfMansionBusinessException("発言残り回数が0回です")
        // 文字数
        messageContent.assertMessageLength(length)
    }

    fun remainingCount(
        status: VillageStatus,
        latestDayMessageList: List<Message>
    ): Int {
        if (status.isPrologue() || status.isEpilogue()) {
            return count // プロローグ、エピローグでは制限なし状態にする
        }
        val alreadySayCount = latestDayMessageList.count { it.content.type.toCdef() == messageType.toCdef() }
        return count - alreadySayCount
    }

    fun existsDifference(restrict: VillageMessageRestrict): Boolean {
        return skill.code != restrict.skill.code
            || messageType.code != restrict.messageType.code
            || count != restrict.count
            || length != restrict.length
    }
}
