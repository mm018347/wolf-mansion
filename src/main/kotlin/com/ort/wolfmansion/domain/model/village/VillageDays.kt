package com.ort.wolfmansion.domain.model.village

data class VillageDays(
    val list: List<VillageDay>
) {

    fun day(day: Int): VillageDay = list.first { it.day == day }

    fun existsDifference(days: VillageDays): Boolean {
        if (list.size != days.list.size) return true
        return list.any { day1 -> days.list.none { day2 -> !day1.existsDifference(day2) } }
    }
}
