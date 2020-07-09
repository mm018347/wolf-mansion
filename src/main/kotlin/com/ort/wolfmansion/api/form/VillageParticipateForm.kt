package com.ort.wolfmansion.api.form

import org.jetbrains.annotations.NotNull

data class VillageParticipateForm(
    /** キャラクター */
    @field:NotNull
    val charaId: Int? = null,

    /** 希望役職 */
    val requestedSkill: String? = null,

    /** 第二希望役職 */
    val secondRequestedSkill: String? = null,

    /** 入村発言 */
    @field:NotNull
    val joinMessage: String? = null,

    /** 入村人数（管理者用） */
    val personNumber: Int? = null,

    /** 入村パスワード */
    val joinPassword: String? = null,

    /** 見学か */
    val spectator: Boolean? = null
)
