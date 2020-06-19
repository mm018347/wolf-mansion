package com.ort.wolfmansion.domain.model.village

import com.ort.wolfmansion.domain.model.dead.Dead
import com.ort.wolfmansion.domain.model.skill.Skill
import com.ort.wolfmansion.domain.model.skill.SkillRequest

data class VillageParticipant(
    val id: Int,
    val charaId: Int,
    val playerId: Int,
    val roomNo: Int?,
    val dead: Dead?,
    val isSpectator: Boolean,
    val isGone: Boolean,
    val skill: Skill?,
    val skillRequest: SkillRequest,
    val isWin: Boolean?
) {
    fun existsDifference(participant: VillageParticipant): Boolean {
        if (id != participant.id) return true
        if (charaId != participant.charaId) return true
        if (playerId != participant.playerId) return true
        if (dead?.code != participant.dead?.code) return true
        if (isSpectator != participant.isSpectator) return true
        if (isGone != participant.isGone) return true
        if (skill?.code != participant.skill?.code) return true
        if (skillRequest.first.code != participant.skillRequest.first.code) return true
        if (skillRequest.second.code != participant.skillRequest.second.code) return true
        return false
    }
}
