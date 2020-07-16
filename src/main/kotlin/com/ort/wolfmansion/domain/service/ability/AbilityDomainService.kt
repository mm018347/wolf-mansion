package com.ort.wolfmansion.domain.service.ability

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.ability.AbilityType
import com.ort.wolfmansion.domain.model.ability.AbilityTypes
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.myself.AbilitySituation
import com.ort.wolfmansion.domain.model.myself.AbilitySituations
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.ability.VillageAbilities
import com.ort.wolfmansion.domain.model.village.ability.VillageAbility
import com.ort.wolfmansion.domain.model.village.footstep.VillageFootsteps
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.fw.exception.WolfMansionBusinessException
import org.springframework.stereotype.Service

@Service
class AbilityDomainService(
    private val attackDomainService: AttackService,
    private val divineService: DivineService,
    private val guardService: GuardService,
    private val investigationService: InvestigationService
) {
    fun createAbilitySetMessage(
        village: Village,
        abilityType: AbilityType,
        myself: VillageParticipant,
        target: VillageParticipant?,
        footstep: String?
    ): Message {
        val text = createSetMessage(abilityType, myself, target, footstep)
        return Message.createPrivateSystemMessage(text, village.days.latestDay().day)
    }

    fun assertAbility(
        village: Village,
        participant: VillageParticipant?,
        abilityType: AbilityType,
        targetId: Int?,
        villageAbilities: VillageAbilities
    ) {
        participant?.skill ?: throw WolfMansionBusinessException("役職なし")
        // その能力を持っていない
        if (AbilityTypes(participant.skill).list.none { it.code == abilityType.code }) throw WolfMansionBusinessException("${abilityType.name}の能力を持っていません")
        // 使えない状況
        if (!isUsable(village, participant, abilityType)) throw WolfMansionBusinessException("${abilityType.name}能力を使えない状態です")
        // 対象指定がおかしい
        if (targetId == null && !canNoTarget(abilityType)) throw WolfMansionBusinessException("対象指定が必要です")
        if (targetId != null && getSelectableTargetList(
                village,
                participant,
                abilityType,
                villageAbilities
            ).none { it.id == targetId }
        ) throw WolfMansionBusinessException("指定できない対象を指定しています")
    }

    // 日付更新時のデフォルト能力行使を追加
    fun createDefaultAbilities(
        village: Village,
        abilities: VillageAbilities
    ): VillageAbilities {
        val abilityList = mutableListOf<VillageAbility>()

        // 襲撃
        val attackAbilityList = attackDomainService.getDefaultAbilityList(village, abilities)
        if (attackAbilityList.isNotEmpty()) abilityList.addAll(attackAbilityList)
        // 占い
        val divineAbilityList = divineService.getDefaultAbilityList(village, abilities)
        if (divineAbilityList.isNotEmpty()) abilityList.addAll(divineAbilityList)
        // 護衛
        val guardAbilityList = guardService.getDefaultAbilityList(village, abilities)
        if (guardAbilityList.isNotEmpty()) abilityList.addAll(guardAbilityList)

        return VillageAbilities(abilityList)
    }

    fun convertToSituation(
        participant: VillageParticipant?,
        village: Village,
        abilities: VillageAbilities,
        footsteps: VillageFootsteps
    ): AbilitySituations {
        return AbilitySituations(
            list = participant?.skill?.let {
                AbilityTypes(it).list.map { abilityType ->
                    AbilitySituation(
                        type = abilityType,
                        selectableAttackerList = listOf(), // TODO
                        attacker = null, // TODO
                        targetSelectAbility = abilityType.isTargetSelectAbility(),
                        selectableTargetList = getSelectableTargetList(village, participant, abilityType, abilities),
                        target = getSelectingTarget(village, participant, abilities, abilityType),
                        footstepSelectAbility = abilityType.isFootstepSelectAbility(),
                        selectableFootstepList = getSelectableFootstepList(village, participant, abilityType, footsteps),
                        footstep = getSelectingFootstep(village, participant, abilities, abilityType),
                        usable = isUsable(village, participant, abilityType),
                        availableNoTarget = canNoTarget(abilityType)
                    )
                }
            } ?: listOf()
        )
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun detectDomainService(abilityType: AbilityType): IAbilityDomainService? {
        return when (abilityType.code) {
            CDef.AbilityType.襲撃.code() -> attackDomainService
            CDef.AbilityType.占い.code() -> divineService
            CDef.AbilityType.護衛.code() -> guardService
            else -> null
        }
    }

    // 選択可能な対象
    private fun getSelectableTargetList(
        village: Village,
        participant: VillageParticipant?,
        abilityType: AbilityType,
        villageAbilities: VillageAbilities
    ): List<VillageParticipant> {
        if (!canUseAbility(village, participant)) return listOf()
        return detectDomainService(abilityType)?.getSelectableTargetList(village, participant, villageAbilities) ?: listOf()
    }

    private fun getSelectingTarget(
        village: Village,
        participant: VillageParticipant?,
        villageAbilities: VillageAbilities,
        abilityType: AbilityType
    ): VillageParticipant? {
        if (!canUseAbility(village, participant)) return null
        return detectDomainService(abilityType)?.getSelectingTarget(village, participant, villageAbilities)
    }

    private fun getSelectableFootstepList(
        village: Village,
        participant: VillageParticipant?,
        abilityType: AbilityType,
        footsteps: VillageFootsteps
    ): List<String> {
        if (!canUseAbility(village, participant)) return listOf()
        return detectDomainService(abilityType)?.getSelectableFootstepList(village, participant, footsteps) ?: listOf()
    }

    private fun getSelectingFootstep(
        village: Village,
        participant: VillageParticipant,
        abilities: VillageAbilities,
        abilityType: AbilityType): String? {
        if (!canUseAbility(village, participant)) return null
        return detectDomainService(abilityType)?.getSelectingFootstep(village, participant, abilities)
    }

    private fun canUseAbility(village: Village, participant: VillageParticipant?): Boolean {
        // 村として可能か
        if (!village.canUseAbility()) return false
        // 参加者として可能か
        participant ?: return false
        return participant.canUseAbility()
    }

    private fun isUsable(
        village: Village,
        participant: VillageParticipant?,
        abilityType: AbilityType
    ): Boolean {
        participant ?: return false
        // 進行中でないと使えない
        if (!village.status.isProgress()) return false
        return detectDomainService(abilityType)?.isUsable(village, participant) ?: false
    }

    private fun canNoTarget(abilityType: AbilityType): Boolean {
        return detectDomainService(abilityType)?.isAvailableNoTarget() ?: false
    }

    private fun createSetMessage(
        abilityType: AbilityType,
        myself: VillageParticipant,
        target: VillageParticipant?,
        footstep: String?
    ): String {
        return detectDomainService(abilityType)?.createSetMessage(myself, target, footstep)
            ?: throw IllegalStateException("想定外の能力")
    }
}