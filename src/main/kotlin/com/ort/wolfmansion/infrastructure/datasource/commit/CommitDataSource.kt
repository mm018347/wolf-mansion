package com.ort.wolfmansion.infrastructure.datasource.commit

import com.ort.dbflute.exbhv.CommitBhv
import com.ort.dbflute.exentity.Commit
import com.ort.wolfmansion.domain.model.commit.Commits
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import org.springframework.stereotype.Repository

@Repository
class CommitDataSource(
    val commitBhv: CommitBhv
) {

    // ===================================================================================
    //                                                                              Select
    //                                                                              ======
    fun findCommit(
        village: com.ort.wolfmansion.domain.model.village.Village,
        participant: VillageParticipant
    ): com.ort.wolfmansion.domain.model.commit.Commit? {
        val latestDay = village.days.latestDay()

        val optCommit = commitBhv.selectEntity {
            it.query().setVillageId_Equal(village.id)
            it.query().setDay_Equal(latestDay.day)
            it.query().setVillagePlayerId_Equal(participant.id)
        }
        return optCommit.map { c ->
            com.ort.wolfmansion.domain.model.commit.Commit(
                day = c.day,
                myselfId = c.villagePlayerId,
                isCommitting = true
            )
        }.orElse(null)
    }

    fun findCommits(villageId: Int): Commits {
        val commitList = commitBhv.selectList {
            it.query().queryVillageDay().setVillageId_Equal(villageId)
        }
        return Commits(
            list = commitList.map { c ->
                com.ort.wolfmansion.domain.model.commit.Commit(
                    day = c.day,
                    myselfId = c.villagePlayerId,
                    isCommitting = true
                )
            }
        )
    }

    // ===================================================================================
    //                                                                              Update
    //                                                                              ======
    fun updateCommit(villageId: Int, commit: com.ort.wolfmansion.domain.model.commit.Commit) {
        deleteCommit(villageId, commit)
        if (commit.isCommitting) insertCommit(villageId, commit)
    }

    private fun deleteCommit(villageId: Int, commit: com.ort.wolfmansion.domain.model.commit.Commit) {
        commitBhv.queryDelete {
            it.query().setVillageId_Equal(villageId)
            it.query().setDay_Equal(commit.day)
            it.query().setVillagePlayerId_Equal(commit.myselfId)
        }
    }

    private fun insertCommit(villageId: Int, c: com.ort.wolfmansion.domain.model.commit.Commit) {
        val commit = Commit()
        commit.villageId = villageId
        commit.day = c.day
        commit.villagePlayerId = c.myselfId
        commitBhv.insert(commit)
    }
}