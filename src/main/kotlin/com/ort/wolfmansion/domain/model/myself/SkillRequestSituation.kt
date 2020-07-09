package com.ort.wolfmansion.domain.model.myself

import com.ort.wolfmansion.domain.model.skill.Skill
import com.ort.wolfmansion.domain.model.skill.SkillRequest

data class SkillRequestSituation(
    val isAvailableSkillRequest: Boolean,
    val selectableSkillList: List<Skill> = listOf(),
    val skillRequest: SkillRequest?
)
