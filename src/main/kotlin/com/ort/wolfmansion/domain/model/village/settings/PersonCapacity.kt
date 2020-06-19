package com.ort.wolfmansion.domain.model.village.settings

data class PersonCapacity(
    val min: Int,
    val max: Int
) {
    fun existsDifference(capacity: PersonCapacity): Boolean {
        return max != capacity.max || min != capacity.min
    }
}
