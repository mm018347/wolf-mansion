package com.ort.wolfmansion.domain.model.village.vote

data class VillageVotes(
    val list: List<VillageVote>
) {
    fun existsDifference(votes: VillageVotes): Boolean {
        return list.size != votes.list.size
    }

    fun add(villageVotes: VillageVotes): VillageVotes {
        return VillageVotes(this.list + villageVotes.list)
    }

    fun filterByMyself(id: Int): VillageVotes {
        return this.copy(list = list.filter { it.myselfId == id })
    }

    fun filterByDay(day: Int): VillageVotes {
        return this.copy(list = list.filter { it.day == day })
    }
}