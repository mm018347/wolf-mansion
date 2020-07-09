package com.ort.wolfmansion.api.view.domain.model.myself

import com.ort.wolfmansion.domain.model.myself.AbilitySituations
import com.ort.wolfmansion.domain.model.village.Village

data class AbilitySituationsView(
    val list: List<AbilitySituationView>
) {
    constructor(
        situation: AbilitySituations,
        village: Village
    ) : this(
        list = situation.list.map { AbilitySituationView(it, village) }
    )
}
