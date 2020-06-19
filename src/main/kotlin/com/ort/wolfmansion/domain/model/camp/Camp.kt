package com.ort.wolfmansion.domain.model.camp

import com.ort.dbflute.allcommon.CDef

data class Camp(
    val code: String,
    val name: String
) {
    constructor(cdef: CDef.Camp) : this(
        code = cdef.code(),
        name = cdef.alias()
    )

    fun toCdef(): CDef.Camp = CDef.Camp.codeOf(this.code)
}