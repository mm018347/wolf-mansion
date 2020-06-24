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

    fun order(): Int {
        return when (toCdef()) {
            CDef.Camp.村人陣営 -> 1
            CDef.Camp.人狼陣営 -> 2
            CDef.Camp.狐陣営 -> 3
            CDef.Camp.愉快犯陣営 -> 4
        }
    }
}