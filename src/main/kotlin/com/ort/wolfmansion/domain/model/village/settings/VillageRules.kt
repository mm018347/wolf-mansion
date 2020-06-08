package com.ort.wolfmansion.domain.model.village.settings

/**
 * TODO 設定内容
 *
 * @property openVote
 * @property availableSkillRequest
 */
data class VillageRules(
    val openVote: Boolean,
    val availableSkillRequest: Boolean,
    val availableSpectate: Boolean,
    val openSkillInGrave: Boolean,
    val visibleGraveMessage: Boolean,
    val availableSuddenlyDeath: Boolean,
    val availableCommit: Boolean,
    val messageRestrict: VillageMessageRestricts
) {

}
