package com.ort.wolfmansion.domain.model.village

import com.ort.wolfmansion.domain.model.village.settings.PersonCapacity
import com.ort.wolfmansion.domain.model.village.settings.VillageCharachip
import com.ort.wolfmansion.domain.model.village.settings.VillageOrganizations
import com.ort.wolfmansion.domain.model.village.settings.VillagePassword
import com.ort.wolfmansion.domain.model.village.settings.VillageRules
import com.ort.wolfmansion.domain.model.village.settings.VillageTime

data class VillageSettings(
    val capacity: PersonCapacity,
    val time: VillageTime,
    val charachip: VillageCharachip,
    val organizations: VillageOrganizations,
    val rules: VillageRules,
    val password: VillagePassword
) {
    fun existsDifference(setting: VillageSettings): Boolean {
        if (capacity.existsDifference(setting.capacity)) return true
        if (time.existsDifference(setting.time)) return true
        if (charachip.existsDifference(setting.charachip)) return true
        if (organizations.existsDifference(setting.organizations)) return true
        if (rules.existsDifference(setting.rules)) return true
        if (password.existsDifference(setting.password)) return true
        return false
    }
}
