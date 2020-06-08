package com.ort.wolfmansion.domain.model.village.settings

import com.ort.wolfmansion.domain.model.message.MessageType
import com.ort.wolfmansion.domain.model.skill.Skill

data class VillageMessageRestrict(
    val skill: Skill,
    val messageType: MessageType,
    val count: Int,
    val length: Int
) {

}
