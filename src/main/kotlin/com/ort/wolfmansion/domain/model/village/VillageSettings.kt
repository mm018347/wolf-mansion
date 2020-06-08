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

}
