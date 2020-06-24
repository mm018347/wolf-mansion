package com.ort.wolfmansion.domain.service.skill

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.fw.exception.WolfMansionBusinessException
import org.springframework.stereotype.Service

@Service
class SkillRequestService {

    fun isAvailableSkillRequest(
        village: Village,
        participant: VillageParticipant?
    ): Boolean {
        // 村として可能か
        if (!village.isAvailableSkillRequest()) return false
        // 参加者として可能か
        participant ?: return true
        return participant.isAvailableSkillRequest()
    }

    /**
     * 役職希望変更チェック
     * @param village village
     * @param participant 参加者
     * @param firstRequestSkill 第1役職希望
     * @param secondRequestSkill 第2役職希望
     */
    fun assertSkillRequest(
        village: Village,
        participant: VillageParticipant?,
        firstRequestSkill: String,
        secondRequestSkill: String
    ) {
        if (!isAvailableSkillRequest(village, participant)) throw WolfMansionBusinessException("役職希望変更できません")
        val first = CDef.Skill.codeOf(firstRequestSkill) ?: throw WolfMansionBusinessException("第1希望が不正")
        val second = CDef.Skill.codeOf(secondRequestSkill) ?: throw WolfMansionBusinessException("第2希望が不正")
        village.assertSkillRequest(first, second)
    }
}