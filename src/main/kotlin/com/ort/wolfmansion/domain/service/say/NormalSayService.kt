package com.ort.wolfmansion.domain.service.say

import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.fw.exception.WolfMansionBusinessException
import org.springframework.stereotype.Service

@Service
class NormalSayService {

    fun isViewable(village: Village, participant: VillageParticipant?): Boolean {
        return true
    }

    fun isSayable(village: Village, participant: VillageParticipant): Boolean {
        // 参加者として可能か
        if (!participant.isSayableNormalSay(village.status.isEpilogue())) return false
        // 村として可能か
        return village.isSayableNormalSay()
    }

    fun assertSay(village: Village, participant: VillageParticipant) {
        if (!isSayable(village, participant)) throw WolfMansionBusinessException("発言できません")
    }
}