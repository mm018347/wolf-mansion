package com.ort.wolfmansion.domain.model.village.settings

data class VillageCharachip(
    val dummyCharaId: Int,
    val charachipId: Int
) {
    fun existsDifference(charachip: VillageCharachip): Boolean {
        return dummyCharaId != charachip.dummyCharaId || charachipId != charachip.charachipId
    }
}
