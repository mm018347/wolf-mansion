package com.ort.wolfmansion.application.service

import com.ort.wolfmansion.domain.model.commit.Commit
import com.ort.wolfmansion.domain.model.commit.Commits
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.infrastructure.datasource.commit.CommitDataSource
import org.springframework.stereotype.Service

@Service
class CommitService(
    val commitDataSource: CommitDataSource
) {

    fun findCommits(
        villageId: Int
    ): Commits = commitDataSource.findCommits(villageId)

    fun findCommit(
        village: Village,
        participant: VillageParticipant?
    ): Commit? {
        participant ?: return null
        return commitDataSource.findCommit(village, participant)
    }

    fun updateCommit(
        villageId: Int,
        commit: Commit
    ) = commitDataSource.updateCommit(villageId, commit)
}