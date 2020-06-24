package com.ort.wolfmansion.application.coordinator

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.application.service.MessageService
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.message.MessageQuery
import com.ort.wolfmansion.domain.model.message.Messages
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.fw.security.WolfMansionUser
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MessageCoordinator(
    val dayChangeCoordinator: DayChangeCoordinator,
    val villageCoordinator: VillageCoordinator,
    val messageService: MessageService,
    // domain service
    val messageDomainDomainService: com.ort.wolfmansion.domain.service.message.MessageDomainService
) {

    fun findMessageList(
        village: Village,
        day: Int,
        noonnight: String,
        user: WolfMansionUser?,
        from: Long?,
        pageSize: Int?,
        pageNum: Int?,
        keyword: String?,
        messageTypeList: List<CDef.MessageType>?,
        participantIdList: List<Int>?
    ): Messages {
        val participant: VillageParticipant? = villageCoordinator.findParticipant(village, user)
        val availableMessageTypeList = messageDomainDomainService.viewableMessageTypeList(village, participant, day, user?.authority)
        val query = MessageQuery(
            myself = participant,
            messageTypeList = messageTypeList,
            pageSize = pageSize,
            pageNum = pageNum,
            availableMessageTypeList = availableMessageTypeList
        )
        val messages: Messages = messageService.findMessages(
            villageId = village.id,
            day = day,
            query = query
        )
        dayChangeCoordinator.dayChangeIfNeeded(village)
        return messages
    }

    fun findMessage(village: Village, messageType: String, messageNumber: Int, user: WolfMansionUser?): Message? {
        val participant: VillageParticipant? = villageCoordinator.findParticipant(village, user)
        return if (!messageDomainDomainService.isViewableMessage(village, participant, messageType)) null
        else messageService.findMessage(village.id, CDef.MessageType.codeOf(messageType), messageNumber) ?: return null
    }

    fun findLatestMessagesUnixTimeMilli(
        village: Village,
        user: WolfMansionUser?
    ): LocalDateTime? {
        val participant: VillageParticipant? = villageCoordinator.findParticipant(village, user)
        val messageTypeList: List<CDef.MessageType> =
            messageDomainDomainService.viewableMessageTypeList(village, participant, village.days.latestDay().day, user?.authority)
        return messageService.findLatestMessageDatetime(village.id, messageTypeList, participant)
    }
}