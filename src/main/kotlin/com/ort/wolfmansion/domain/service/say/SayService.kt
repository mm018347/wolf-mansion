package com.ort.wolfmansion.domain.service.say

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.charachip.Chara
import com.ort.wolfmansion.domain.model.charachip.Charas
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.message.MessageContent
import com.ort.wolfmansion.domain.model.message.MessageType
import com.ort.wolfmansion.domain.model.myself.SayMessageTypeSituation
import com.ort.wolfmansion.domain.model.myself.SayRestrictSituation
import com.ort.wolfmansion.domain.model.myself.SaySituation
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.fw.exception.WolfMansionBusinessException
import org.springframework.stereotype.Service

@Service
class SayService(
    private val normalSayService: NormalSayService,
    private val werewolfSayService: WerewolfSayService,
    private val graveSayService: GraveSayService,
    private val monologueSayService: MonologueSayService,
    private val spectateSayService: SpectateSayService
) {

    private val defaultMessageTypeOrder = listOf(
        CDef.MessageType.人狼の囁き,
        CDef.MessageType.共鳴発言,
        CDef.MessageType.通常発言,
        CDef.MessageType.死者の呻き,
        CDef.MessageType.見学発言,
        CDef.MessageType.独り言
    )

    fun assertSay(
        village: Village,
        participant: VillageParticipant?,
        chara: Chara?,
        latestDayMessageList: List<Message>,
        messageContent: MessageContent
    ) {
        // 事前チェック
        if (!isAvailableSay(village, participant)) throw WolfMansionBusinessException("発言できません")
        // 発言種別ごとのチェック
        // TODO 他にもあるはず　共鳴とか
        when (messageContent.type.toCdef()) {
            CDef.MessageType.通常発言 -> normalSayService.assertSay(village, participant!!)
            CDef.MessageType.人狼の囁き -> werewolfSayService.assertSay(village, participant!!)
            CDef.MessageType.死者の呻き -> graveSayService.assertSay(village, participant!!)
            CDef.MessageType.独り言 -> monologueSayService.isSayable(village, participant!!)
            CDef.MessageType.見学発言 -> spectateSayService.isSayable(village, participant!!)
            else -> throw WolfMansionBusinessException("不正な発言種別です")
        }
        // 表情種別チェック
        if (!isSelectableFaceType(chara!!, messageContent)) throw WolfMansionBusinessException("不正な表情種別です")
        // 発言回数、長さ、行数チェック
        village.assertMessageRestrict(messageContent, latestDayMessageList)
    }

    fun assertParticipateSay(
        village: Village,
        chara: Chara?,
        messageContent: MessageContent
    ) {
        // 事前チェック
        if (!village.isAvailableSay()) throw WolfMansionBusinessException("入村発言できません")
        // 表情種別チェック
        if (!isSelectableFaceType(chara!!, messageContent)) throw WolfMansionBusinessException("不正な表情種別です")
        // 発言長さ、行数チェック
        village.assertMessageRestrict(messageContent, listOf())
    }

    fun convertToSituation(
        village: Village,
        participant: VillageParticipant?,
        latestDayMessageList: List<Message>,
        charas: Charas
    ): SaySituation {
        return SaySituation(
            isAvailableSay = isAvailableSay(village, participant),
            selectableMessageTypeList = selectableMessageTypeList(village, participant)
                .map { cdefMessageType ->
                    SayMessageTypeSituation(
                        messageType = MessageType(cdefMessageType),
                        restrict = village.setting.rules.messageRestrict.restrict(cdefMessageType).let { restrict ->
                            SayRestrictSituation(
                                isRestricted = restrict != null,
                                maxCount = restrict?.count,
                                remainingCount = restrict?.remainingCount(village.status, latestDayMessageList),
                                maxLength = restrict?.length ?: MessageContent.lengthMax,
                                maxLine = MessageContent.lineMax
                            )
                        },
                        targetList = village.participant.list.filterNot { it.id == participant?.id }
                    )
                },
            selectableFaceTypeList = participant?.let { charas.chara(it.chara.id).faces.list } ?: listOf(),
            defaultMessageType = detectDefaultMessageType(village, participant)
        )
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    /**
     * @param village village
     * @param participant 参加者
     * @return 発言できるか
     */
    private fun isAvailableSay(village: Village, participant: VillageParticipant?): Boolean {
        // 参加者として可能か
        participant ?: return false
        if (!participant.isAvailableSay(village.status.isEpilogue())) return false
        // 村として可能か
        if (!village.isAvailableSay()) return false
        return true
    }

    private fun isSelectableFaceType(chara: Chara, messageContent: MessageContent): Boolean =
        chara.faces.list.any { it.type == messageContent.faceCode }

    private fun detectDefaultMessageType(village: Village, participant: VillageParticipant?): MessageType? {
        if (!this.isAvailableSay(village, participant)) return null

        val selectableMessageTypeList = this.selectableMessageTypeList(village, participant)
        if (selectableMessageTypeList.isEmpty()) return null

        val defaultMessageType = defaultMessageTypeOrder
            .firstOrNull { selectableMessageTypeList.contains(it) }
            ?: return null
        return MessageType(defaultMessageType)
    }

    private fun selectableMessageTypeList(village: Village, participant: VillageParticipant?): List<CDef.MessageType> {
        if (!this.isAvailableSay(village, participant)) return listOf()

        val list: MutableList<CDef.MessageType> = mutableListOf()

        if (normalSayService.isSayable(village, participant!!)) list.add(CDef.MessageType.通常発言)
        if (werewolfSayService.isSayable(village, participant)) list.add(CDef.MessageType.人狼の囁き)
        if (graveSayService.isSayable(village, participant)) list.add(CDef.MessageType.死者の呻き)
        if (monologueSayService.isSayable(village, participant)) list.add(CDef.MessageType.独り言)
        if (spectateSayService.isSayable(village, participant)) list.add(CDef.MessageType.見学発言)

        return list
    }
}