package com.ort.wolfmansion.domain.model.village.footstep

import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipants

data class VillageFootsteps(
    val list: List<VillageFootstep>
) {
    private val noFootstepMessage = "足音を聞いたものはいなかった...。"

    fun existsDifference(footsteps: VillageFootsteps): Boolean {
        return list.size != footsteps.list.size
    }

    fun filterLatestDay(village: Village): VillageFootsteps = this.filterByDay(village.days.latestDay().day)

    fun filterYesterday(village: Village): VillageFootsteps = this.filterByDay(village.days.yesterday().day)

    fun filterByDay(day: Int): VillageFootsteps = VillageFootsteps(list = list.filter { it.day == day })

    fun convertToDayDispFootsteps(
        participants: VillageParticipants,
        day: Int // セットした日
    ): VillageFootsteps {
        val footstepList = this
            .filterByDay(day)
            .convertToDispOnlyAlive(participants)
            .filterNot { it.footsteps.isNullOrEmpty() || it.footsteps == "なし" }
        return VillageFootsteps(list = footstepList.sortedBy { it.footsteps })
    }

    fun convertToDayDispFootstepsStr(
        participants: VillageParticipants,
        day: Int // セットした日
    ): String {
        val footstepList = this.convertToDayDispFootsteps(participants, day)
        if (footstepList.list.isEmpty()) return noFootstepMessage
        return footstepList.list.map { it.footsteps }.joinToString("\n")
    }

    fun convertToDayDispFootstepsStrWithSkill(
        participants: VillageParticipants,
        day: Int // セットした日
    ): String {
        return this
            .filterByDay(day)
            .list.joinToString("\n") {
            it.convertToDispStrWithSkill(participants)
        }
    }

    private fun convertToDispOnlyAlive(participants: VillageParticipants): List<VillageFootstep> {
        return list.map { it.convertToDispOnlyAlive(participants) }
    }
}