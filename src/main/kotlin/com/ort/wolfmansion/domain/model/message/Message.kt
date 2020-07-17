package com.ort.wolfmansion.domain.model.message

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.util.WolfMansionDateUtil

data class Message(
    val fromParticipantId: Int?,
    val toParticipantId: Int?,
    val time: MessageTime,
    val content: MessageContent
) {

    companion object {

        fun createPublicSystemMessage(text: String, day: Int): Message =
            createSystemMessage(MessageType(CDef.MessageType.公開システムメッセージ), text, day)

        fun createPrivateSystemMessage(text: String, day: Int): Message =
            createSystemMessage(MessageType(CDef.MessageType.非公開システムメッセージ), text, day)

        fun createSayMessage(
            from: VillageParticipant,
            to: VillageParticipant? = null,
            day: Int,
            messageContent: MessageContent
        ): Message = Message(
            fromParticipantId = from.id,
            toParticipantId = to?.id,
            time = MessageTime(
                day = day,
                datetime = WolfMansionDateUtil.currentLocalDateTime() // dummy
            ),
            content = messageContent
        )

        // TODO 全体的に足音が不足

        // 白黒霊視メッセージ
        fun createPsychicPrivateMessage(text: String, villageDayId: Int): Message =
            createSystemMessage(MessageType(CDef.MessageType.白黒霊視結果), text, villageDayId)

        // 襲撃メッセージ TODO 囁きにする
        fun createAttackPrivateMessage(text: String, day: Int): Message =
            createSystemMessage(MessageType(CDef.MessageType.襲撃結果), text, day)

        // 占い
        fun createSeerPrivateMessage(text: String, day: Int, participant: VillageParticipant): Message =
            createSystemMessage(MessageType(CDef.MessageType.白黒占い結果), text, day, participant)

        // 捜査
        fun createInvastigatePrivateMessage(text: String, day: Int, participant: VillageParticipant): Message =
            createSystemMessage(MessageType(CDef.MessageType.足音調査結果), text, day, participant)

        // ===================================================================================
        //                                                                        Assist Logic
        //                                                                        ============
        private fun createSystemMessage(
            messageType: MessageType,
            text: String,
            day: Int,
            from: VillageParticipant? = null
        ): Message = Message(
            fromParticipantId = from?.id,
            toParticipantId = null,
            time = MessageTime(
                day = day,
                datetime = WolfMansionDateUtil.currentLocalDateTime() // dummy
            ),
            content = MessageContent(
                type = messageType,
                num = 0, // dummy
                text = text,
                convertDisable = true,
                faceCode = null
            )
        )
    }
}