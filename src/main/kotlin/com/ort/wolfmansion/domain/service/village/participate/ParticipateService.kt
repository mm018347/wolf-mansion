package com.ort.wolfmansion.domain.service.village.participate

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.charachip.Chara
import com.ort.wolfmansion.domain.model.charachip.Charas
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.myself.ParticipateSituation
import com.ort.wolfmansion.domain.model.player.Player
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.fw.exception.WolfMansionBusinessException
import org.springframework.stereotype.Service

@Service
class ParticipateService {

    /**
     * 参加チェック
     */
    fun assertParticipate(
        player: Player?,
        village: Village,
        charaId: Int,
        firstRequestSkill: CDef.Skill,
        secondRequestSkill: CDef.Skill,
        isSpectate: Boolean,
        password: String?
    ) {
        // 参加できない状況ならNG
        if (!isAvailableParticipate(player, village)) throw WolfMansionBusinessException("参加できません")
        // 既にそのキャラが参加していたりパスワードを間違えていたらNG
        village.assertParticipate(charaId, password)
        // 役職希望無効の場合はおまかせのみ
        if (!isSpectate && !village.setting.rules.isValidSkillRequest(
                firstRequestSkill,
                secondRequestSkill
            )
        ) throw WolfMansionBusinessException("希望役職が不正です")
    }

    /**
     * 見学チェック
     */
    fun assertSpectate(
        player: Player?,
        village: Village,
        charaId: Int,
        charachipCharaNum: Int,
        password: String?
    ) {
        if (!isAvailableSpectate(player, village, charachipCharaNum)) throw WolfMansionBusinessException("見学できません")
        // 既にそのキャラが参加していたりパスワードを間違えていたらNG
        village.assertParticipate(charaId, password)
    }

    fun createParticipateMessage(
        village: Village,
        chara: Chara,
        isSpectate: Boolean
    ): Message {
        // 何人目か
        val number =
            if (isSpectate) village.spectator.count
            else village.participant.count

        val text =
            if (isSpectate) "（見学）${number}人目、${chara.name.fullName()}。"
            else "${number}人目、${chara.name.fullName()}。"

        return Message.createPublicSystemMessage(text, village.days.prologueDay().day)
    }

    /**
     * 退村チェック
     */
    fun assertLeave(
        village: Village,
        participant: VillageParticipant?
    ) {
        if (!isAvailableLeave(village, participant)) throw WolfMansionBusinessException("退村できません")
    }

    /**
     * 退村メッセージ e.g. {キャラ名}は村を去った。
     */
    fun createLeaveMessage(village: Village, chara: Chara): Message =
        Message.createPublicSystemMessage(createLeaveMessageString(chara), village.days.latestDay().day)

    fun convertToSituation(
        participant: VillageParticipant?,
        player: Player?,
        village: Village,
        charas: Charas
    ): ParticipateSituation {
        return ParticipateSituation(
            isParticipating = participant != null,
            isAvailableParticipate = isAvailableParticipate(player, village),
            isAvailableSpectate = isAvailableSpectate(player, village, charas.list.size),
            selectableCharaList = getSelectableCharaList(village, charas),
            isAvailableLeave = isAvailableLeave(village, participant)
        )
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun createLeaveMessageString(chara: Chara): String =
        "${chara.name.fullName()}は村を去った。"

    // 参加可能な状況か
    private fun isAvailableParticipate(
        player: Player?,
        village: Village
    ): Boolean {
        // プレイヤーとして参加可能か
        player ?: return false
        if (!player.isAvailableParticipate()) return false
        // 村として参加可能か
        return village.isAvailableParticipate()
    }

    /**
     * 見学可能な状況か
     */
    private fun isAvailableSpectate(
        player: Player?,
        village: Village,
        charachipCharaNum: Int
    ): Boolean {
        // プレイヤーとして参加可能か
        player ?: return false
        if (!player.isAvailableParticipate()) return false
        // 村として見学可能か
        return village.isAvailableSpectate(charachipCharaNum)
    }

    /**
     * 退村可能か
     */
    private fun isAvailableLeave(
        village: Village,
        participant: VillageParticipant?
    ): Boolean {
        // 村として退村可能か
        if (!village.isAvailableLeave()) return false
        // 参加していない
        participant ?: return false

        return true
    }

    /**
     * 参加/見学できるキャラ
     */
    private fun getSelectableCharaList(village: Village, charas: Charas): List<Chara> {
        return charas.list.filterNot { chara ->
            village.participant.list.any { it.chara.id == chara.id }
                || village.spectator.list.any { it.chara.id == chara.id }
        }
    }
}