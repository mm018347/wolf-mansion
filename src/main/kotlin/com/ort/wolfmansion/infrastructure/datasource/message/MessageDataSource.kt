package com.ort.wolfmansion.infrastructure.datasource.message

import com.ort.dbflute.allcommon.CDef
import com.ort.dbflute.cbean.MessageCB
import com.ort.dbflute.exbhv.MessageBhv
import com.ort.dbflute.exentity.Message
import com.ort.wolfmansion.domain.model.message.MessageContent
import com.ort.wolfmansion.domain.model.message.MessageQuery
import com.ort.wolfmansion.domain.model.message.MessageTime
import com.ort.wolfmansion.domain.model.message.MessageType
import com.ort.wolfmansion.domain.model.message.Messages
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.fw.exception.WolfMansionBusinessException
import com.ort.wolfmansion.util.WolfMansionDateUtil
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class MessageDataSource(
    val messageBhv: MessageBhv
) {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private val logger = LoggerFactory.getLogger(MessageDataSource::class.java)

    // ===================================================================================
    //                                                                             Execute
    //                                                                           =========
    /**
     * 発言取得
     */
    fun findMessages(
        villageId: Int,
        day: Int,
        query: MessageQuery
    ): Messages {
        if (query.messageTypeList.isEmpty()) return Messages(listOf())

        val messageList = messageBhv.selectPage { cb ->
            // 内容
            queryMessage(cb, villageId, day, query)
            // ページング
            queryPaging(cb, query)
            // order
            cb.query().addOrderBy_MessageDatetime_Asc()
            cb.query().addOrderBy_MessageId_Asc()
        }
        return Messages(
            list = messageList.map { convertMessageToMessage(it) },
            allRecordCount = messageList.allRecordCount,
            allPageCount = messageList.allPageCount,
            isExistPrePage = messageList.existsPreviousPage(),
            isExistNextPage = messageList.existsNextPage(),
            currentPageNum = messageList.currentPageNumber
        )
    }

    /**
     * 最新発言時間取得
     */
    fun findLatestMessageDatetime(
        villageId: Int,
        messageTypeList: List<CDef.MessageType>,
        myself: VillageParticipant?
    ): LocalDateTime? {
        val query = MessageQuery(
            pageSize = null,
            pageNum = null,
            myself = myself,
            messageTypeList = messageTypeList
        )
        return messageBhv.selectScalar(LocalDateTime::class.java).max {
            queryMessage(it, villageId, null, query)
        }.orElse(null)
    }

    /**
     * アンカー発言取得
     */
    fun findMessage(
        villageId: Int,
        messageType: CDef.MessageType,
        messageNumber: Int
    ): com.ort.wolfmansion.domain.model.message.Message? {
        return messageBhv.selectEntity {
            it.query().setVillageId_Equal(villageId)
            it.query().setMessageNumber_Equal(messageNumber)
            it.query().setMessageTypeCode_Equal_AsMessageType(messageType)
        }.map { convertMessageToMessage(it) }.orElse(null)
    }

    /**
     * 参加者のその日の発言を取得
     */
    fun selectParticipateDayMessageList(
        villageId: Int,
        day: Int,
        myself: VillageParticipant
    ): List<com.ort.wolfmansion.domain.model.message.Message> {
        val messageList = messageBhv.selectList {
            it.query().setVillageId_Equal(villageId)
            it.query().setVillagePlayerId_Equal(myself.id)
            it.query().setDay_Equal(day)
        }
        return messageList.map { convertMessageToMessage(it) }
    }

    fun registerMessage(villageId: Int, message: com.ort.wolfmansion.domain.model.message.Message) {
        val mes = Message()
        val messageType = message.content.type.toCdef()
        mes.villageId = villageId
        mes.day = message.time.day
        mes.messageTypeCodeAsMessageType = messageType
        mes.messageContent = message.content.text
        mes.villagePlayerId = message.fromParticipantId
        mes.toVillagePlayerId = message.toParticipantId
        mes.playerId = null // TODO プレイヤー発言を実装する際に実装する
        mes.faceTypeCodeAsFaceType = CDef.FaceType.codeOf(message.content.faceCode)
        mes.isConvertDisable = true
        mes.messageDatetime = WolfMansionDateUtil.currentLocalDateTime()

        // 発言番号の採番 & insert (3回チャレンジする)
        repeat(3) {
            try {
                mes.messageNumber = selectNextMessageNumber(villageId, messageType)
                messageBhv.insert(mes)
                return
            } catch (e: RuntimeException) {
                logger.error(e.message, e)
            }
        }
        throw WolfMansionBusinessException("混み合っているため発言に失敗しました。再度発言してください。")
    }


    /**
     * 差分更新
     * @param villageId villageId
     * @param before messages
     * @param after messages
     */
    fun updateDifference(villageId: Int, before: Messages, after: Messages) {
        // 追加しかないのでbeforeにないindexから追加していく
        after.list.drop(before.list.size).forEach {
            registerMessage(villageId, it)
        }
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun selectNextMessageNumber(villageId: Int, messageType: CDef.MessageType): Int {
        val maxNumber: Int = messageBhv.selectScalar(Int::class.java).max {
            it.specify().columnMessageNumber()
            it.query().setVillageId_Equal(villageId)
            it.query().setMessageTypeCode_Equal_AsMessageType(messageType)
        }.orElse(0)
        return maxNumber + 1
    }

    private fun convertMessageToMessage(message: Message): com.ort.wolfmansion.domain.model.message.Message {
        return com.ort.wolfmansion.domain.model.message.Message(
            fromParticipantId = message.villagePlayerId,
            toParticipantId = message.toVillagePlayerId,
            time = MessageTime(
                day = message.day,
                datetime = message.messageDatetime
            ),
            content = MessageContent(
                type = MessageType(
                    code = message.messageTypeCode,
                    name = CDef.MessageType.codeOf(message.messageTypeCode).alias()
                ),
                num = message.messageNumber,
                text = message.messageContent,
                convertDisable = message.isConvertDisable,
                faceCode = message.faceTypeCode
            )
        )
    }

    private fun queryMessage(
        cb: MessageCB,
        villageId: Int,
        day: Int?,
        query: MessageQuery
    ) {
        cb.query().setVillageId_Equal(villageId)
        day?.let { cb.query().setDay_Equal(it) }
        if (query.myself != null) {
            val participantId = query.myself.id
            cb.orScopeQuery { orCB ->
                orCB.query().setMessageTypeCode_InScope_AsMessageType(query.messageTypeList)
                orCB.orScopeQueryAndPart { andCB -> queryMyMonologue(andCB, participantId) }
                orCB.orScopeQueryAndPart { andCB -> queryMySecretSay(andCB, participantId) }
                orCB.orScopeQueryAndPart { andCB -> querySecretSayToMe(andCB, participantId) }
                if (query.myself.isViewablePrivateAbilityMessage()) orCB.orScopeQueryAndPart { andCB ->
                    queryMyPrivateAbility(
                        andCB,
                        participantId
                    )
                }
            }
        } else {
            cb.query().setMessageTypeCode_InScope_AsMessageType(query.messageTypeList)
        }
    }

    private fun queryMyMonologue(cb: MessageCB, participantId: Int) {
        cb.query().setMessageTypeCode_Equal_独り言()
        cb.query().setVillagePlayerId_Equal(participantId)
    }

    private fun queryMySecretSay(cb: MessageCB, participantId: Int) {
        cb.query().setMessageTypeCode_Equal_秘話()
        cb.query().setVillagePlayerId_Equal(participantId)
    }

    private fun querySecretSayToMe(cb: MessageCB, participantId: Int) {
        cb.query().setMessageTypeCode_Equal_秘話()
        cb.query().setToVillagePlayerId_Equal(participantId)
    }

    private fun queryMyPrivateAbility(cb: MessageCB, participantId: Int) {
        cb.query().setMessageTypeCode_InScope_AsMessageType(MessageType.personalPrivateAbilityList)
        cb.query().setVillagePlayerId_Equal(participantId)
    }

    private fun queryPaging(cb: MessageCB, query: MessageQuery) {
        if (query.pageNum != null && query.pageSize != null) cb.paging(query.pageSize, query.pageNum)
        else if (query.pageSize != null) cb.paging(query.pageSize, 10000) // 存在しないページを検索して最新を取得させる
        else cb.paging(100000, 1)
    }
}