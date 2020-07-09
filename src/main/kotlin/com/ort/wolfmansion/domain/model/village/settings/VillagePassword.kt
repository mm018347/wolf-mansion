package com.ort.wolfmansion.domain.model.village.settings

data class VillagePassword(
    val joinPassword: String?
) {
    companion object {
        const val joinPassword_length_min = 3
        const val joinPassword_length_max = 12
    }

    fun existsDifference(password: VillagePassword): Boolean {
        return joinPassword != password.joinPassword
    }
}
