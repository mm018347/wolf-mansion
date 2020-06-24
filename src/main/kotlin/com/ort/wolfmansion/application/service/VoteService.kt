package com.ort.wolfmansion.application.service

import com.ort.wolfmansion.domain.model.village.vote.VillageVote
import com.ort.wolfmansion.domain.model.village.vote.VillageVotes
import com.ort.wolfmansion.infrastructure.datasource.vote.VoteDataSource
import org.springframework.stereotype.Service

@Service
class VoteService(
    private val voteDataSource: VoteDataSource
) {

    fun findVillageVotes(villageId: Int): VillageVotes = voteDataSource.findVotes(villageId)

    fun updateVote(villageId: Int, villageVote: VillageVote) =
        voteDataSource.updateVote(villageId, villageVote)

    fun updateDifference(villageId: Int, before: VillageVotes, after: VillageVotes) {
        voteDataSource.updateDifference(villageId, before, after)
    }
}