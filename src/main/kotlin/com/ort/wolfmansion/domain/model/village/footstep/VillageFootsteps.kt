package com.ort.wolfmansion.domain.model.village.footstep

data class VillageFootsteps(
    val list: List<VillageFootstep>
) {
    fun existsDifference(footsteps: VillageFootsteps): Boolean {
        return list.size != footsteps.list.size
    }
}