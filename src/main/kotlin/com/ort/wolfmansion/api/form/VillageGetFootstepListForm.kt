package com.ort.wolfmansion.api.form

data class VillageGetFootstepListForm(
    /** 実行者キャラID（狼のみ） */
    val charaId: Int? = null,
    /** 対象キャラID */
    val targetCharaId: Int? = null
)
