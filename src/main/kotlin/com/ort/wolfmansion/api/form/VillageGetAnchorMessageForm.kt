package com.ort.wolfmansion.api.form

import org.jetbrains.annotations.NotNull

data class VillageGetAnchorMessageForm(
    /** 発言番号 */
    @field:NotNull
    val messageNumber: Int? = null,

    /** 発言種別 */
    @field:NotNull
    val messageType: String? = null
)
