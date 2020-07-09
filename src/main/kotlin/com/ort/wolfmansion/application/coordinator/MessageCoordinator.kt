package com.ort.wolfmansion.application.coordinator

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.application.service.MessageService
import com.ort.wolfmansion.application.service.VillageService
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
    val villageService: VillageService,
    val messageService: MessageService,
    // domain service
    val messageDomainDomainService: com.ort.wolfmansion.domain.service.message.MessageDomainService
) {

    fun findMessages(
        village: Village,
        day: Int,
        user: WolfMansionUser?,
        pageSize: Int?,
        pageNum: Int?
    ): Messages {
        val participant: VillageParticipant? = villageCoordinator.findParticipant(village, user)
        val availableMessageTypeList =
            messageDomainDomainService.viewableMessageTypeList(
                village,
                participant,
                day,
                user?.authority
            )
        val query = MessageQuery(
            myself = participant,
            pageSize = pageSize,
            pageNum = pageNum,
            availableMessageTypeList = availableMessageTypeList
        )
        val messages: Messages = messageService.findMessages(
            villageId = village.id,
            day = day,
            query = query
        )
        // 最新アクセス日時を更新
        participant?.let { villageService.updateLastAccessDatetime(village.id, it.id) }
        // 日付更新
        dayChangeCoordinator.dayChangeIfNeeded(village)
        return messages
    }

    fun findMessage(village: Village, messageType: String, messageNumber: Int, user: WolfMansionUser?): Message? {
        val participant: VillageParticipant? = villageCoordinator.findParticipant(village, user)
        return if (!messageDomainDomainService.isViewableMessage(village, participant, messageType)) null
        else messageService.findMessage(village.id, CDef.MessageType.codeOf(messageType), messageNumber) ?: return null
    }

    fun findLatestMessagesDatetime(
        village: Village,
        user: WolfMansionUser?
    ): LocalDateTime? {
        val participant: VillageParticipant? = villageCoordinator.findParticipant(village, user)
        val messageTypeList: List<CDef.MessageType> =
            messageDomainDomainService.viewableMessageTypeList(village, participant, village.days.latestDay().day, user?.authority)
        return messageService.findLatestMessageDatetime(village.id, messageTypeList, participant)
    }
}