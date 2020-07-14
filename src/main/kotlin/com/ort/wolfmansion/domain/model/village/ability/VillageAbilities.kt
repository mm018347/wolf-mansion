package com.ort.wolfmansion.domain.model.village.ability

import com.ort.wolfmansion.domain.model.ability.AbilityType
import com.ort.wolfmansion.domain.model.village.Village


data class VillageAbilities(
    val list: List<VillageAbility>
) {

    fun filterLatestday(village: Village): VillageAbilities =
        this.filterByDay(village.days.latestDay().day)

    fun filterYesterday(village: Village): VillageAbilities =
        this.filterByDay(village.days.yesterday().day)

    fun filterByDay(day: Int): VillageAbilities =
        this.copy(list = list.filter { it.day == day })

    fun filterByAbility(abilityType: AbilityType): VillageAbilities =
        this.copy(list = list.filter { it.abilityType.code == abilityType.code })

    fun existsDifference(abilities: VillageAbilities): Boolean = list.size != abilities.list.size

    fun add(villageAbilities: VillageAbilities): VillageAbilities {
        return VillageAbilities(this.list + villageAbilities.list)
    }
}