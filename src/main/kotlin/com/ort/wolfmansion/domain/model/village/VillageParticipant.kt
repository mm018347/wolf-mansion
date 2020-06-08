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
    val isWin: Boolean
) {

}
