package com.ort.wolfmansion.api.view.model.village_list

import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.Villages

data class VillageListModel(
    val villageList: List<VillageListVillageModel>
) {
    constructor(
        villages: Villages
    ) : this(
        villageList = villages.list.sortedByDescending { it.id }.map { VillageListVillageModel(it) }
    )
}

data class VillageListVillageModel(
    val villageId: Int,
    val villageNumber: String,
    val villageName: String,
    val participateNum: String,
    val status: String
) {
    constructor(
        village: Village
    ) : this(
        villageId = village.id,
        villageNumber = village.id.toString().padStart(4, '0'),
        villageName = village.name,
        participateNum = participateNum(village),
        status = village.status.name
    )

    companion object {
        fun participateNum(village: Village): String {
            val participateCount = village.participant.count
            val capacity = village.setting.capacity.max
            return "${participateCount}/${capacity}人"
        }
    }
}