package com.ort.wolfmansion.domain.model.village

import com.ort.dbflute.allcommon.CDef

data class VillageStatus(
    val code: String,
    val name: String
) {
    constructor(cdef: CDef.VillageStatus) : this(
        code = cdef.code(),
        name = cdef.alias()
    )

    fun toCdef(): CDef.VillageStatus = CDef.VillageStatus.codeOf(this.code)
}
