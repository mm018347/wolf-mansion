package com.ort.wolfmansion.domain.model.ability

import com.ort.wolfmansion.domain.model.skill.Skill

data class AbilityTypes(
    val list: List<AbilityType>
) {

    constructor(
        skill: Skill
    ) : this(
        list = skill.getAbilityTypes().list
    )
}