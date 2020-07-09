package com.ort.wolfmansion.domain.service.village.settings

import com.ort.wolfmansion.domain.model.myself.CreatorSituation
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import org.springframework.stereotype.Service

@Service
class SettingsService {

    fun convertToSituation(
        village: Village,
        participant: VillageParticipant?
    ): CreatorSituation {
        return CreatorSituation(
            isCreator = isCreator(village, participant),
            isAvailableSettingsUpdate = isAvailableSettingsUpdate(village, participant)
        )
    }

    private fun isCreator(
        village: Village,
        participant: VillageParticipant?
    ): Boolean {
        return participant?.player?.name == village.creatorPlayerName
    }

    private fun isAvailableSettingsUpdate(
        village: Village,
        participant: VillageParticipant?
    ): Boolean {
        // プロローグの間だけ
        if (!village.status.isPrologue()) return false
        // 国主か作成者
        return this.isCreator(village, participant)
            || village.dummyParticipant().id == participant?.id
    }
}