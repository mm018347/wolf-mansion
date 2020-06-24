package com.ort.wolfmansion.domain.service.message

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.domain.service.say.GraveSayService
import com.ort.wolfmansion.domain.service.say.MonologueSayService
import com.ort.wolfmansion.domain.service.say.NormalSayService
import com.ort.wolfmansion.domain.service.say.SpectateSayService
import com.ort.wolfmansion.domain.service.say.WerewolfSayService
import com.ort.wolfmansion.domain.service.say.secretSayService
import org.springframework.stereotype.Service

@Service
class MessageDomainService(
    private val normalSayService: NormalSayService,
    private val werewolfSayService: WerewolfSayService,
    private val graveSayService: GraveSayService,
    private val monologueSayService: MonologueSayService,
    private val spectateSayService: SpectateSayService,
    private val secretSayService: secretSayService,
    private val psychicMessageService: psychicMessageService,
    private val attackMessageService: AttackMessageService
) {

    private val everyoneAllowedMessageTypeList = listOf(
        CDef.MessageType.公開システムメッセージ,
        CDef.MessageType.通常発言,
        CDef.MessageType.村建て発言
    )

    // 閲覧できる発言種別リスト
    fun viewableMessageTypeList(
        village: Village,
        participant: VillageParticipant?,
        day: Int,
        authority: CDef.Authority?
    ): List<CDef.MessageType> {
        // 管理者は全て見られる
        if (authority == CDef.Authority.管理者) return CDef.MessageType.listAll()
        // 村が終了していたら全て見られる
        if (village.status.isSolved()) return CDef.MessageType.listAll()

        val allowedTypeList: MutableList<CDef.MessageType> = mutableListOf()
        allowedTypeList.addAll(everyoneAllowedMessageTypeList)
        // 権限に応じて追加していく（独り言と秘話はここでは追加しない）
        listOf(
            CDef.MessageType.死者の呻き,
            CDef.MessageType.見学発言,
            CDef.MessageType.人狼の囁き,
            CDef.MessageType.白黒霊視結果,
            CDef.MessageType.襲撃結果
        ).forEach {
            if (isViewableMessage(village, participant, it.code())) allowedTypeList.add(it)
        }
        return allowedTypeList
    }

    fun isViewableMessage(
        village: Village,
        participant: VillageParticipant?,
        messageType: String,
        day: Int = 1
    ): Boolean {
        return when (CDef.MessageType.codeOf(messageType) ?: return false) {
            CDef.MessageType.通常発言 -> normalSayService.isViewable(village, participant)
            CDef.MessageType.人狼の囁き -> werewolfSayService.isViewable(village, participant)
            CDef.MessageType.死者の呻き -> graveSayService.isViewable(village, participant)
            CDef.MessageType.見学発言 -> spectateSayService.isViewable(village, participant, day)
            CDef.MessageType.独り言 -> monologueSayService.isViewable(village, participant)
            CDef.MessageType.秘話 -> secretSayService.isViewable(village, participant)
            CDef.MessageType.白黒霊視結果 -> psychicMessageService.isViewable(village, participant)
            CDef.MessageType.襲撃結果 -> attackMessageService.isViewable(village, participant)
            else -> return false
        }
    }
}