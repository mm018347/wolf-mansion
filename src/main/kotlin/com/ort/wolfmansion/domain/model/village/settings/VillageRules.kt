package com.ort.wolfmansion.domain.model.village.settings

data class VillageRules(
    val openVote: Boolean,
    val availableSkillRequest: Boolean,
    val availableSpectate: Boolean,
    val availableSameWolfAttack: Boolean,
    val openSkillInGrave: Boolean,
    val visibleGraveMessage: Boolean,
    val availableSuddenlyDeath: Boolean,
    val availableCommit: Boolean,
    val availableGuardSameTarget: Boolean,
    val messageRestrict: VillageMessageRestricts
) {
    fun existsDifference(rules: VillageRules): Boolean {
        return openVote != rules.openVote
            || availableSkillRequest != rules.availableSkillRequest
            || availableSpectate != rules.availableSpectate
            || availableSameWolfAttack != rules.availableSameWolfAttack
            || openSkillInGrave != rules.openSkillInGrave
            || visibleGraveMessage != rules.visibleGraveMessage
            || availableSuddenlyDeath != rules.availableSuddenlyDeath
            || availableCommit != rules.availableCommit
            || availableGuardSameTarget != rules.availableGuardSameTarget
            || messageRestrict.existsDifference(rules.messageRestrict)
    }
}
