package com.ort.wolfmansion.infrastructure.datasource.ability

import com.ort.dbflute.allcommon.CDef
import com.ort.dbflute.exbhv.AbilityBhv
import com.ort.dbflute.exbhv.VillagePlayerBhv
import com.ort.dbflute.exentity.Ability
import com.ort.wolfmansion.domain.model.village.ability.VillageAbilities
import com.ort.wolfmansion.domain.model.village.ability.VillageAbility
import org.springframework.stereotype.Repository

@Repository
class AbilityDataSource(
    val abilityBhv: AbilityBhv,
    val villagePlayerBhv: VillagePlayerBhv
) {

    // ===================================================================================
    //                                                                              Select
    //                                                                              ======
    fun findAbilities(villageId: Int): VillageAbilities {
        val abilityList = abilityBhv.selectList {
            it.query().queryVillageDay().setVillageId_Equal(villageId)
            it.setupSelect_CharaByCharaId()
            it.setupSelect_CharaByTargetCharaId()
        }
        abilityBhv.load(abilityList) { loader ->
            loader.pulloutCharaByCharaId().loadVillagePlayer {
                it.query().setVillageId_Equal(villageId)
                it.query().setIsGone_Equal_False()
            }
            loader.pulloutCharaByTargetCharaId().loadVillagePlayer {
                it.query().setVillageId_Equal(villageId)
                it.query().setIsGone_Equal_False()
            }
        }
        return VillageAbilities(abilityList.map { convertToAbilityToVillageAbility(it) })
    }

    // ===================================================================================
    //                                                                              Update
    //                                                                              ======
    fun updateAbility(villageId: Int, villageAbility: VillageAbility) {
        deleteAbility(villageId, villageAbility)
        insertAbility(villageId, villageAbility)
    }

    fun updateDifference(villageId: Int, before: VillageAbilities, after: VillageAbilities) {
        after.list.drop(before.list.size).forEach {
            insertAbility(villageId, it)
        }
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun deleteAbility(villageId: Int, villageAbility: VillageAbility) {
        abilityBhv.queryDelete {
            it.query().setVillageId_Equal(villageId)
            it.query().setDay_Equal(villageAbility.day)
            // 襲撃だけは連動するので誰がセットしたかは無関係
            if (villageAbility.abilityType.toCdef() != CDef.AbilityType.襲撃) {
                it.query().queryCharaByCharaId().existsVillagePlayer { vpCB ->
                    vpCB.query().setVillageId_Equal(villageId)
                    vpCB.query().setIsGone_Equal_False()
                }
            }
            it.query().setAbilityTypeCode_Equal_AsAbilityType(villageAbility.abilityType.toCdef())
        }
    }

    private fun insertAbility(villageId: Int, villageAbility: VillageAbility) {
        val ability = Ability()
        ability.villageId = villageId
        ability.day = villageAbility.day
        ability.charaId = selectCharaIdByVillagePlayerId(villageId, villageAbility.myselfId)
        ability.targetCharaId = villageAbility.targetId?.let { selectCharaIdByVillagePlayerId(villageId, it) }
        ability.targetFootstep = villageAbility.targetFootstep
        ability.abilityTypeCodeAsAbilityType = villageAbility.abilityType.toCdef()
        abilityBhv.insert(ability)
    }

    private fun selectCharaIdByVillagePlayerId(villageId: Int, villagePlayerId: Int): Int {
        return villagePlayerBhv.selectEntityWithDeletedCheck {
            it.query().setVillageId_Equal(villageId)
            it.query().setVillagePlayerId_Equal(villagePlayerId)
            it.query().setIsGone_Equal_False()
        }.charaId
    }

    // ===================================================================================
    //                                                                             Mapping
    //                                                                             =======
    private fun convertToAbilityToVillageAbility(ability: Ability): VillageAbility {
        return VillageAbility(
            day = ability.day,
            myselfId = ability.charaByCharaId.get().villagePlayerList.first().villagePlayerId,
            targetId = ability.charaByTargetCharaId.map { it.villagePlayerList.first().villagePlayerId }.orElse(null),
            targetFootstep = ability.targetFootstep,
            abilityType = com.ort.wolfmansion.domain.model.ability.AbilityType(ability.abilityTypeCodeAsAbilityType)
        )
    }
}