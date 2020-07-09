package com.ort.wolfmansion.api.form

data class VillageAbilityForm(
    /** 行使キャラID(人狼のみ) */
    val charaId: Int? = null,
    /** 対象キャラID(人狼、占い、狩人のみ) */
    val targetCharaId: Int? = null,
    /** 足音(人狼、占い、妖狐、狂人、探偵のみ) */
    val footstep: String? = null
)
