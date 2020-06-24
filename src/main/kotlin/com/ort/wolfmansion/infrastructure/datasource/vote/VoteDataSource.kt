package com.ort.wolfmansion.infrastructure.datasource.vote

import com.ort.dbflute.exbhv.VillagePlayerBhv
import com.ort.dbflute.exbhv.VoteBhv
import com.ort.dbflute.exentity.Vote
import com.ort.wolfmansion.domain.model.village.vote.VillageVote
import com.ort.wolfmansion.domain.model.village.vote.VillageVotes
import org.springframework.stereotype.Repository

@Repository
class VoteDataSource(
    val voteBhv: VoteBhv,
    val villagePlayerBhv: VillagePlayerBhv
) {

    // ===================================================================================
    //                                                                              Select
    //                                                                              ======
    fun findVotes(villageId: Int): VillageVotes {
        val voteList = voteBhv.selectList {
            it.query().queryVillageDay().setVillageId_Equal(villageId)
        }
        voteBhv.load(voteList) { loader ->
            loader.pulloutCharaByCharaId().loadVillagePlayer {
                it.query().setVillageId_Equal(villageId)
                it.query().setIsGone_Equal_False()
            }
            loader.pulloutCharaByVoteCharaId().loadVillagePlayer {
                it.query().setVillageId_Equal(villageId)
                it.query().setIsGone_Equal_False()
            }
        }
        return VillageVotes(voteList.map { convertToVoteToVillageVote(it) })
    }

    // ===================================================================================
    //                                                                              Update
    //                                                                              ======
    fun updateVote(villageId: Int, villageVote: VillageVote) {
        deleteVote(villageId, villageVote)
        insertVote(villageId, villageVote)
    }

    private fun deleteVote(villageId: Int, villageVote: VillageVote) {
        voteBhv.queryDelete {
            it.query().queryCharaByCharaId().existsVillagePlayer { vpCB ->
                vpCB.query().setVillageId_Equal(villageId)
                vpCB.query().setVillagePlayerId_Equal(villageVote.myselfId)
                vpCB.query().setIsGone_Equal_False()
            }
            it.query().queryCharaByVoteCharaId().existsVillagePlayer { vpCB ->
                vpCB.query().setVillageId_Equal(villageId)
                vpCB.query().setVillagePlayerId_Equal(villageVote.targetId)
                vpCB.query().setIsGone_Equal_False()
            }
        }
    }

    private fun insertVote(villageId: Int, villageVote: VillageVote) {
        val vote = Vote()
        vote.villageId = villageId
        vote.day = villageVote.day
        vote.charaId = selectCharaIdByVillagePlayerId(villageId, villageVote.myselfId)
        vote.voteCharaId = selectCharaIdByVillagePlayerId(villageId, villageVote.targetId)
        voteBhv.insert(vote)
    }

    fun updateDifference(villageId: Int, before: VillageVotes, after: VillageVotes) {
        // 削除
        before.list.filterNot { beforeVote ->
            after.list.any { afterVote ->
                beforeVote.day == afterVote.day
                    && beforeVote.myselfId == afterVote.myselfId
            }
        }.forEach { deleteVote(villageId, it) }
        // 更新
        after.list.filter { afterVote ->
            before.list.any { beforeVote ->
                beforeVote.day == afterVote.day
                    && beforeVote.myselfId == afterVote.myselfId
            }
        }.forEach { updateVote(villageId, it) }
        // 追加
        after.list.filterNot { afterVote ->
            before.list.any { beforeVote ->
                beforeVote.day == afterVote.day
                    && beforeVote.myselfId == afterVote.myselfId
            }
        }.forEach { insertVote(villageId, it) }
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun convertToVoteToVillageVote(vote: Vote): VillageVote {
        return VillageVote(
            day = vote.day,
            myselfId = vote.charaByCharaId.get().villagePlayerList.first().villagePlayerId,
            targetId = vote.charaByVoteCharaId.get().villagePlayerList.first().villagePlayerId
        )
    }

    private fun selectCharaIdByVillagePlayerId(villageId: Int, villagePlayerId: Int): Int {
        return villagePlayerBhv.selectEntityWithDeletedCheck {
            it.query().setVillageId_Equal(villageId)
            it.query().setVillagePlayerId_Equal(villagePlayerId)
            it.query().setIsGone_Equal_False()
        }.charaId
    }
}