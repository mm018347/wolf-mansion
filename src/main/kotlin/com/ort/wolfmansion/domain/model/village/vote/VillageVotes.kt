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
}