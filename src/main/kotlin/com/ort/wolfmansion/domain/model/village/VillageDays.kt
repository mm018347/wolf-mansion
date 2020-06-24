package com.ort.wolfmansion.domain.model.village

data class VillageDays(
    val list: List<VillageDay>
) {

    private val extendHours: Long = 24L

    fun day(day: Int): VillageDay = list.first { it.day == day }

    fun prologueDay(): VillageDay {
        check(list.isNotEmpty()) { "have no days." }
        return list.first()
    }

    fun yesterday(): VillageDay {
        check(list.size > 1) { "have no yesterday." }
        return list[list.size - 2]
    }

    fun latestDay(): VillageDay {
        check(list.isNotEmpty()) { "have no days." }
        return list.last()
    }

    fun existsDifference(days: VillageDays): Boolean {
        if (list.size != days.list.size) return true
        return list.any { day1 -> days.list.none { day2 -> !day1.existsDifference(day2) } }
    }

    fun extendLatestDay(): VillageDays {
        return this.copy(list = list.map {
            if (it.day == latestDay().day) latestDay().copy(dayChangeDatetime = yesterday().dayChangeDatetime.plusHours(extendHours))
            else it
        })
    }
}
