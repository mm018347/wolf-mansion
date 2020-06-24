package com.ort.wolfmansion.domain.service.message

import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import org.springframework.stereotype.Service

@Service
class psychicMessageService {

    fun isViewable(village: Village, participant: VillageParticipant?): Boolean {
        // いずれかを満たせばok
        // 村として可能か
        if (village.isViewablePsychicMessage()) return true
        // 参加者として可能か
        participant ?: return false
        return participant.isViewablePsychicMessage()
    }
}