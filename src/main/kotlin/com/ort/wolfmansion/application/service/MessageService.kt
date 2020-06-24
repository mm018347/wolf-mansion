package com.ort.wolfmansion.application.service

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.ability.AbilityType
import com.ort.wolfmansion.domain.model.charachip.Chara
import com.ort.wolfmansion.domain.model.charachip.Charas
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.message.MessageContent
import com.ort.wolfmansion.domain.model.message.MessageQuery
import com.ort.wolfmansion.domain.model.message.Messages
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.domain.service.ability.AbilityDomainService
import com.ort.wolfmansion.domain.service.commit.CommitDomainService
import com.ort.wolfmansion.domain.service.village.participate.LeaveService
import com.ort.wolfmansion.domain.service.village.participate.ParticipateService
import com.ort.wolfmansion.infrastructure.datasource.message.MessageDataSource
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MessageService(
    // infra
    val messageDataSource: MessageDataSource,
    // domain service
    val abilityDomainService: AbilityDomainService,
    val participateService: ParticipateService,
    val leaveService: LeaveService,
    val commitDomainService: CommitDomainService
) {

    /**
     * 発言取得
     */
    fun findMessages(
        villageId: Int,
        day: Int,
        query: MessageQuery
    ): Messages {
        return messageDataSource.findMessages(villageId, day, query)
    }

    /**
     * 最新発言日時取得
     */
    fun findLatestMessageDatetime(
        villageId: Int,
        messageTypeList: List<CDef.MessageType>,
        participant: VillageParticipant? = null
    ): LocalDateTime? {
        return messageDataSource.findLatestMessageDatetime(villageId, messageTypeList, participant)
    }

    /**
     * アンカー発言取得
     */
    fun findMessage(
        villageId: Int,
        messageType: CDef.MessageType,
        messageNumber: Int
    ): Message? {
        return messageDataSource.findMessage(villageId, messageType, messageNumber)
    }

    /**
     * 参加者のその日の発言を取得
     */
    fun findParticipateDayMessageList(
        villageId: Int,
        villageDay: com.ort.wolfmansion.domain.model.village.VillageDay,
        participant: VillageParticipant?
    ): List<Message> {
        participant ?: return listOf()
        return messageDataSource.selectParticipateDayMessageList(villageId, villageDay.day, participant)
    }

    /**
     * 発言登録
     */
    fun registerSayMessage(
        villageId: Int,
        message: Message
    ) = messageDataSource.registerMessage(villageId, message)

    /**
     * 村作成時のシステムメッセージ登録
     */
    fun registerInitialMessage(
        village: Village
    ) = messageDataSource.registerMessage(village.id, village.createVillagePrologueMessage())

    /**
     * 村に参加する際の発言を登録
     */
    fun registerParticipateMessage(
        village: Village,
        participant: VillageParticipant,
        chara: Chara,
        message: String,
        isSpectate: Boolean
    ) {
        // {N}人目、{キャラ名}。
        messageDataSource.registerMessage(
            village.id,
            participateService.createParticipateMessage(village, chara, isSpectate)
        )
        // 参加発言
        val messageContent = MessageContent(
            CDef.MessageType.通常発言,
            message,
            true, // TODO
            CDef.FaceType.通常
        )
        messageDataSource.registerMessage(
            village.id,
            Message.createSayMessage(
                from = participant,
                day = village.days.prologueDay().day,
                messageContent = messageContent
            )
        )
    }

    /**
     * 退村する際のシステムメッセージを登録
     */
    fun registerLeaveMessage(
        village: Village,
        chara: Chara
    ) = messageDataSource.registerMessage(
        village.id,
        leaveService.createLeaveMessage(village, chara)
    )

    /**
     * 能力セットする際のシステムメッセージを登録
     */
    fun registerAbilitySetMessage(
        village: Village,
        myself: VillageParticipant,
        targetId: Int?,
        abilityType: AbilityType,
        charas: Charas
    ) {
        val myChara: Chara = charas.chara(myself.charaId)
        val targetChara: Chara? = if (targetId == null) null else charas.chara(village.participant, targetId)
        val message: Message = abilityDomainService.createAbilitySetMessage(village, abilityType, myChara, targetChara)
        messageDataSource.registerMessage(village.id, message)
    }

    /**
     * コミットする際のシステムメッセージを登録
     *
     * @param village village
     * @param chara キャラ
     * @param doCommit コミット/取り消し
     */
    fun registerCommitMessage(village: Village, chara: Chara, doCommit: Boolean) {
        messageDataSource.registerMessage(
            village.id,
            commitDomainService.createCommitMessage(chara, doCommit, village.days.latestDay().day)
        )
    }

    /**
     * 差分更新
     * @param villageId villageId
     * @param before messages
     * @param after messages
     */
    fun updateDifference(villageId: Int, before: Messages, after: Messages) {
        messageDataSource.updateDifference(villageId, before, after)
    }
}