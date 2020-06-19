package com.ort.wolfmansion.domain.model.skill

import com.ort.dbflute.allcommon.CDef

data class Skill(
    val code: String,
    val name: String
) {
    constructor(cdef: CDef.Skill) : this(
        code = cdef.code(),
        name = cdef.alias()
    )

    fun toCdef(): CDef.Skill = CDef.Skill.codeOf(this.code)
}