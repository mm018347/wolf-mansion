package com.ort.wolfmansion.infrastructure.datasource.footstep

import com.ort.dbflute.exbhv.FootstepBhv
import com.ort.dbflute.exbhv.VillagePlayerBhv
import com.ort.dbflute.exentity.Footstep
import com.ort.wolfmansion.domain.model.village.footstep.VillageFootstep
import com.ort.wolfmansion.domain.model.village.footstep.VillageFootsteps
import org.springframework.stereotype.Repository

@Repository
class FootstepDataSource(
    val footstepBhv: FootstepBhv,
    val villagePlayerBhv: VillagePlayerBhv
) {

    // ===================================================================================
    //                                                                              Select
    //                                                                              ======
    fun findFootsteps(villageId: Int): VillageFootsteps {
        val footstepList = footstepBhv.selectList {
            it.query().queryVillageDay().setVillageId_Equal(villageId)
        }
        footstepBhv.load(footstepList) { loader ->
            loader.pulloutChara().loadVillagePlayer {
                it.query().setVillageId_Equal(villageId)
                it.query().setIsGone_Equal_False()
            }
        }
        return VillageFootsteps(footstepList.map { convertToFootstepToVillageFootstep(it) })
    }

    // ===================================================================================
    //                                                                              Update
    //                                                                              ======
    fun updateFootstep(villageId: Int, villageFootstep: VillageFootstep) {
        deleteFootstep(villageId, villageFootstep)
        insertFootstep(villageId, villageFootstep)
    }

    fun updateDifference(villageId: Int, before: VillageFootsteps, after: VillageFootsteps) {
        after.list.drop(before.list.size).forEach {
            insertFootstep(villageId, it)
        }
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun deleteFootstep(villageId: Int, villageFootstep: VillageFootstep) {
        footstepBhv.queryDelete {
            it.query().setVillageId_Equal(villageId)
            it.query().setDay_Equal(villageFootstep.day)
            it.query().queryChara().existsVillagePlayer { vpCB ->
                vpCB.query().setVillageId_Equal(villageId)
                vpCB.query().setIsGone_Equal_False()
            }
        }
    }

    private fun insertFootstep(villageId: Int, villageFootstep: VillageFootstep) {
        val footstep = Footstep()
        footstep.villageId = villageId
        footstep.day = villageFootstep.day
        footstep.charaId = selectCharaIdByVillagePlayerId(villageId, villageFootstep.myselfId)
        footstep.footstepRoomNumbers = villageFootstep.footsteps
        footstepBhv.insert(footstep)
    }

    private fun selectCharaIdByVillagePlayerId(villageId: Int, villagePlayerId: Int): Int {
        return villagePlayerBhv.selectEntityWithDeletedCheck {
            it.query().setVillageId_Equal(villageId)
            it.query().setVillagePlayerId_Equal(villagePlayerId)
            it.query().setIsGone_Equal_False()
        }.charaId
    }

    // ===================================================================================
    //                                                                             Mapping
    //                                                                             =======
    private fun convertToFootstepToVillageFootstep(footstep: Footstep): VillageFootstep {
        return VillageFootstep(
            day = footstep.day,
            myselfId = footstep.chara.get().villagePlayerList.first().villagePlayerId,
            footsteps = footstep.footstepRoomNumbers
        )
    }
}