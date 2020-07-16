package com.ort.wolfmansion.domain.model.ability

import com.ort.dbflute.allcommon.CDef

data class AbilityType(
    val code: String,
    val name: String
) {
    constructor(
        cdef: CDef.AbilityType
    ) : this(
        code = cdef.code(),
        name = cdef.alias()
    )

    fun toCdef(): CDef.AbilityType = CDef.AbilityType.codeOf(this.code)

    fun isTargetSelectAbility(): Boolean = toCdef().isTargetSelectType

    fun isFootstepSelectAbility(): Boolean = toCdef().isFootstepSelectType
}