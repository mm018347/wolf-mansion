package com.ort.wolfmansion.domain.model.village.footstep

import com.ort.wolfmansion.domain.model.village.participant.VillageParticipants

data class VillageFootstep(
    val day: Int,
    val myselfId: Int,
    val footsteps: String?
) {
    private val noFootstep: String = "なし"

    fun convertToDispOnlyAlive(participants: VillageParticipants): VillageFootstep {
        if (footsteps == null || footsteps == noFootstep) return this.copy(footsteps = noFootstep)

        val footstepList = footsteps.split(",").filter {
            // 死亡していなければ残す
            val participant = participants.findByRoomNo(Integer.parseInt(it))
            participant != null &&
                (participant.isAlive() || participant.dead!!.villageDay.day > day + 1)
        }.map { it.padStart(2, '0') }

        return if (footstepList.isEmpty()) this.copy(footsteps = noFootstep)
        else this.copy(footsteps = footstepList.joinToString(", "))
    }

    fun convertToDispStrOnlyAlive(participants: VillageParticipants): String {
        return this.convertToDispOnlyAlive(participants).footsteps!!
    }

    fun convertToDispStrWithSkill(participants: VillageParticipants): String {
        // 出した人
        val name = participants.member(myselfId).shortName()
        // 役職
        val skillName = participants.member(myselfId).skill!!.name
        // 出そうとした音
        val setFootstep = convertToDispStr()
        // 出た音
        val actualFootstep = convertToDispOnlyAlive(participants)

        return "[$name][$skillName] $setFootstep → $actualFootstep"
    }

    private fun convertToDispStr(): String {
        if (footsteps == null || footsteps == noFootstep) return noFootstep
        return footsteps.split(",").joinToString(", ") { it.padStart(2, '0') }
    }
}