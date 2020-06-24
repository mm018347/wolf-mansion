package com.ort.wolfmansion.application.service

import com.ort.wolfmansion.domain.model.village.footstep.VillageFootstep
import com.ort.wolfmansion.domain.model.village.footstep.VillageFootsteps
import com.ort.wolfmansion.infrastructure.datasource.footstep.FootstepDataSource
import org.springframework.stereotype.Service

@Service
class FootstepService(
    private val footstepDataSource: FootstepDataSource
) {

    fun findVillageFootsteps(villageId: Int): VillageFootsteps = footstepDataSource.findFootsteps(villageId)

    fun updateVote(villageId: Int, villageFootstep: VillageFootstep) =
        footstepDataSource.updateFootstep(villageId, villageFootstep)

    fun updateDifference(villageId: Int, before: VillageFootsteps, after: VillageFootsteps) =
        footstepDataSource.updateDifference(villageId, before, after)
}