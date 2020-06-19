package com.ort.wolfmansion.domain.model.village

data class VillageParticipants(
    val count: Int,
    val list: List<VillageParticipant> = listOf()
) {
    fun member(id: Int): VillageParticipant = list.first { it.id == id }

    fun existsDifference(participant: VillageParticipants): Boolean {
        if (count != participant.count) return true
        if (list.size != participant.list.size) return true
        return list.any { member1 ->
            participant.list.none { member2 -> !member1.existsDifference(member2) }
        }
    }
}
