package com.ort.wolfmansion.api.form

import org.jetbrains.annotations.NotNull

data class VillageSayForm(
    /** 発言 */
    @field:NotNull
    var message: String? = null,

    /** 発言種別 */
    @field:NotNull
    val messageType: String? = null,

    /** 秘話相手 */
    val secretSayTargetCharaId: Int? = null,

    /** 変換無効にするか */
    val isConvertDisable: Boolean? = null,

    /** 表情種別 */
    val faceType: String? = null
) {
}
