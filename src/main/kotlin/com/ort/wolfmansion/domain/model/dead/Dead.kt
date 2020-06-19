package com.ort.wolfmansion.domain.model.dead

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.village.VillageDay

data class Dead(
    val code: String,
    val reason: String,
    val villageDay: VillageDay
) {
    constructor(
        cdef: CDef.DeadReason,
        villageDay: VillageDay
    ) : this(
        code = cdef.code(),
        reason = cdef.alias(),
        villageDay = villageDay
    )

    fun toCdef(): CDef.DeadReason = CDef.DeadReason.codeOf(code)
}