package com.ort.wolfmansion.domain.service.village.participate

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.charachip.Chara
import com.ort.wolfmansion.domain.model.charachip.Charas
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.player.Player
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.fw.exception.WolfMansionBusinessException
import org.springframework.stereotype.Service

@Service
class ParticipateService {

    /**
     * 参加可能な状況か
     */
    fun isAvailableParticipate(
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
     * 見学可能な状況か
     */
    fun isAvailableSpectate(
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

    /**
     * 参加/見学できるキャラ
     */
    fun getSelectableCharaList(village: Village, charas: Charas): List<Chara> {
        return charas.list.filterNot { chara ->
            village.participant.list.any { it.charaId == chara.id }
                || village.spectator.list.any { it.charaId == chara.id }
        }
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
}