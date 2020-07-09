package com.ort.wolfmansion.domain.model.village.room

data class VillageRoom(
    val no: Int,
    val displayNo: String
) {
    constructor(
        no: Int
    ) : this(
        no = no,
        displayNo = no.toString().padStart(2, '0')
    )
}
