package com.ort.wolfmansion.api.view.response

data class VillageSayConfirmResponse(
    /** 発言 */
    val message: VillageMessageResponse,
    /** ランダムキーワード(カンマ区切り) */
    val randomKeywords: String
)
