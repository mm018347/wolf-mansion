package com.ort.wolfmansion.domain.model.village.settings

data class VillagePassword(
    val joinPassword: String?
) {
    fun existsDifference(password: VillagePassword): Boolean {
        return joinPassword != password.joinPassword
    }
}
