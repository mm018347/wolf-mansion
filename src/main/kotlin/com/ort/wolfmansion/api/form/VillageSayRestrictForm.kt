package com.ort.wolfmansion.api.form

import org.jetbrains.annotations.NotNull
import javax.validation.Valid

data class VillageSayRestrictForm(
    /** 役職コード */
    @field:NotNull
    val skillCode: String? = null,

    /** 詳細 */
    @field:NotNull
    @Valid
    val detailList: List<VillageSayRestrictDetailForm>? = null
)

data class VillageSayRestrictDetailForm(
    /** 発言種別コード */
    @field:NotNull
    val messageTypeCode: String? = null,

    /** 制限するか */
    val restrict: Boolean? = null,

    /** 発言回数 */
    val count: Int? = null,

    /** 文字数 */
    val length: Int? = null
)
