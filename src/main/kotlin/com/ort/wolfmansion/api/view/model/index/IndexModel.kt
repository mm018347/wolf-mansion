package com.ort.wolfmansion.api.view.model.index

import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.Villages

data class IndexModel(
    val villageList: List<IndexVillageModel>,
    val isAvailableCreateVillage: Boolean
) {
    constructor(
        villages: Villages,
        isAvailableCreateVillage: Boolean
    ) : this(
        villageList = villages.list.map { IndexVillageModel(it) },
        isAvailableCreateVillage = isAvailableCreateVillage
    )
}

data class IndexVillageModel(
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