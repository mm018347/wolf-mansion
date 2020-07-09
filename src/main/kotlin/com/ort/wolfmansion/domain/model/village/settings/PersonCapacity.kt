package com.ort.wolfmansion.domain.model.village.settings

data class PersonCapacity(
    val min: Int,
    val max: Int
) {

    companion object {
        const val min_min: Int = 8
        const val max_max: Int = 99
    }


    fun existsDifference(capacity: PersonCapacity): Boolean {
        return max != capacity.max || min != capacity.min
    }
}
