package com.ort.wolfmansion.domain.model.player

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.fw.exception.WolfMansionBusinessException
import com.ort.wolfmansion.fw.security.WolfMansionUser

data class Player(
    val id: Int,
    val name: String,
    val isRestrictedParticipation: Boolean,
    val participatingNotSolvedVillageIdList: List<Int> = listOf(),
    val createNotSolvedVillageIdList: List<Int> = listOf()
) {
    fun existsDifference(player: Player): Boolean {
        return id != player.id || isRestrictedParticipation != player.isRestrictedParticipation
    }

    fun assertCreateVillage(user: WolfMansionUser) {
        if (!isAvailableCreateVillage(user)) throw WolfMansionBusinessException("村を作成できません")
    }

    fun isAvailableCreateVillage(user: WolfMansionUser?): Boolean {
        user ?: return false
        if (user.authority == CDef.Authority.管理者) return true
        if (isParticipatingNotSolvedVillage()) return false
        if (isRestrictedParticipation) return false
        if (existsNotSolvedCreateVillage()) return false
        return true
    }

    fun isAvailableParticipate(): Boolean {
        if (isParticipatingNotSolvedVillage()) return false
        if (isRestrictedParticipation) return false
        return true
    }

    fun restrictParticipation(): Player = this.copy(isRestrictedParticipation = true)

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun isParticipatingNotSolvedVillage(): Boolean = participatingNotSolvedVillageIdList.isNotEmpty()

    private fun existsNotSolvedCreateVillage(): Boolean = createNotSolvedVillageIdList.isNotEmpty()


}