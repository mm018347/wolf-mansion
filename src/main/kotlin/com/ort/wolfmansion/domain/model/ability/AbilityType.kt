package com.ort.wolfmansion.domain.model.ability

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.charachip.Chara
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.village.Village

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


}