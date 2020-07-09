package com.ort.wolfmansion.domain.service.ability

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.ability.AbilityType
import com.ort.wolfmansion.domain.model.ability.AbilityTypes
import com.ort.wolfmansion.domain.model.charachip.Chara
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.myself.AbilitySituation
import com.ort.wolfmansion.domain.model.myself.AbilitySituations
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.ability.VillageAbilities
import com.ort.wolfmansion.domain.model.village.ability.VillageAbility
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.fw.exception.WolfMansionBusinessException
import org.springframework.stereotype.Service

@Service
class AbilityDomainService(
    private val attackDomainService: AttackService,
    private val divineService: DivineService,
    private val guardService: GuardService
) {

    fun createAbilitySetMessage(
        village: Village,
        abilityType: AbilityType,
        myChara: Chara,
        targetChara: Chara?
    ): Message {
        val text = createSetMessage(abilityType, myChara, targetChara)
        return Message.createPrivateSystemMessage(text, village.days.latestDay().day)
    }

    fun assertAbility(
        village: Village,
        participant: VillageParticipant?,
        abilityType: AbilityType,
        targetId: Int?
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
                abilityType
            ).none { it.id == targetId }
        ) throw WolfMansionBusinessException("指定できない対象を指定しています")
    }

    // 日付更新時のデフォルト能力行使を追加
    fun createDefaultAbilities(
        village: Village
    ): VillageAbilities {
        val abilityList = mutableListOf<VillageAbility>()

        // 襲撃
        attackDomainService.getDefaultAbility(village)?.let { abilityList.add(it) }
        // 占い
        val divineAbilityList = divineService.getDefaultAbilityList(village)
        if (divineAbilityList.isNotEmpty()) abilityList.addAll(divineAbilityList)
        // 護衛
        val guardAbilityList = guardService.getDefaultAbilityList(village)
        if (guardAbilityList.isNotEmpty()) abilityList.addAll(guardAbilityList)

        return VillageAbilities(abilityList)
    }

    fun convertToSituation(
        participant: VillageParticipant?,
        village: Village,
        abilities: VillageAbilities
    ): AbilitySituations {
        return AbilitySituations(
            list = participant?.skill?.let {
                AbilityTypes(it).list.map { abilityType ->
                    AbilitySituation(
                        type = abilityType,
                        targetList = getSelectableTargetList(village, participant, abilityType),
                        target = getSelectingTarget(village, participant, abilities, abilityType),
                        usable = isUsable(village, participant, abilityType),
                        isAvailableNoTarget = canNoTarget(abilityType)
                    )
                }
            } ?: listOf()
        )
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    // 選択可能な対象
    private fun getSelectableTargetList(
        village: Village,
        participant: VillageParticipant?,
        abilityType: AbilityType
    ): List<VillageParticipant> {
        if (!canUseAbility(village, participant)) return listOf()

        return when (abilityType.code) {
            CDef.AbilityType.襲撃.code() -> attackDomainService.getSelectableTargetList(village, participant)
            CDef.AbilityType.占い.code() -> divineService.getSelectableTargetList(village, participant)
            CDef.AbilityType.護衛.code() -> guardService.getSelectableTargetList(village, participant)
            else -> listOf()
        }
    }

    private fun getSelectingTarget(
        village: Village,
        participant: VillageParticipant?,
        villageAbilities: VillageAbilities,
        abilityType: AbilityType
    ): VillageParticipant? {
        if (!canUseAbility(village, participant)) return null

        return when (abilityType.code) {
            CDef.AbilityType.襲撃.code() -> attackDomainService.getSelectingTarget(village, participant, villageAbilities)
            CDef.AbilityType.占い.code() -> divineService.getSelectingTarget(village, participant, villageAbilities)
            CDef.AbilityType.護衛.code() -> guardService.getSelectingTarget(village, participant, villageAbilities)
            else -> null
        }
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
        return when (abilityType.code) {
            CDef.AbilityType.襲撃.code() -> attackDomainService.isUsable(participant)
            CDef.AbilityType.占い.code() -> divineService.isUsable(participant)
            CDef.AbilityType.護衛.code() -> guardService.isUsable(village, participant)
            else -> false
        }
    }

    private fun canNoTarget(abilityType: AbilityType): Boolean {
        return when (abilityType.code) {
            CDef.AbilityType.襲撃.code() -> attackDomainService.isAvailableNoTarget()
            CDef.AbilityType.占い.code() -> divineService.isAvailableNoTarget()
            CDef.AbilityType.護衛.code() -> guardService.isAvailableNoTarget()
            else -> false
        }
    }

    private fun createSetMessage(
        abilityType: AbilityType,
        myChara: Chara,
        targetChara: Chara?
    ): String {
        // TODO 他にも能力あるはず
        return when (abilityType.toCdef()) {
            CDef.AbilityType.襲撃 -> attackDomainService.createSetMessage(myChara, targetChara)
            CDef.AbilityType.占い -> divineService.createSetMessage(myChara, targetChara)
            CDef.AbilityType.護衛 -> guardService.createSetMessage(myChara, targetChara)
            else -> throw IllegalStateException("想定外の能力")
        }
    }
}