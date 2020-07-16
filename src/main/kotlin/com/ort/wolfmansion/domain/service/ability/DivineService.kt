package com.ort.wolfmansion.domain.service.ability

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.ability.AbilityType
import com.ort.wolfmansion.domain.model.charachip.Chara
import com.ort.wolfmansion.domain.model.daychange.DayChange
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.ability.VillageAbilities
import com.ort.wolfmansion.domain.model.village.ability.VillageAbility
import com.ort.wolfmansion.domain.model.village.footstep.VillageFootsteps
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import org.springframework.stereotype.Service

@Service
class DivineService : IAbilityDomainService {

    override fun getAbilityType(): AbilityType = AbilityType(CDef.AbilityType.占い)

    override fun getSelectableTargetList(
        village: Village,
        participant: VillageParticipant?,
        villageAbilities: VillageAbilities
    ): List<VillageParticipant> {
        participant ?: return listOf()

        // 自分以外の生存者全員
        return village.participant.filterAlive().list.filter { it.id != participant.id }
    }

    override fun getSelectingTarget(village: Village, participant: VillageParticipant?, villageAbilities: VillageAbilities): VillageParticipant? {
        participant ?: return null

        val targetVillageParticipantId = villageAbilities
            .filterLatestday(village)
            .filterByAbility(getAbilityType())
            .list
            .find { it.myselfId == participant.id }?.targetId ?: return null

        return village.participant.member(targetVillageParticipantId)
    }

    override fun getSelectableFootstepList(village: Village, participant: VillageParticipant?, footsteps: VillageFootsteps): List<String> {
        return listOf() // 足音選択型でないので不要
    }

    override fun getSelectingFootstep(village: Village, participant: VillageParticipant?, villageAbilities: VillageAbilities): String? {
        return null // 足音選択型でないので不要
    }

    override fun createSetMessage(
        myself: VillageParticipant,
        target: VillageParticipant?,
        footstep: String?
    ): String =
        "${myself.name()}が占い対象を${checkNotNull(target?.name())}に、通過する部屋を${footstep ?: "なし"}に設定しました。"

    override fun getDefaultAbilityList(village: Village, villageAbilities: VillageAbilities): List<VillageAbility> {
        // 進行中のみ
        if (!village.status.isProgress()) return listOf()
        // 最新日id
        val latestVillageDay = village.days.latestDay()
        // 生存している占い能力持ちごとに
        return village.participant.filterAlive().list.filter {
            it.skill!!.toCdef().isHasDivineAbility
        }.mapNotNull { seer ->
            // 対象は自分以外の生存者からランダム
            this.getSelectableTargetList(village, seer, villageAbilities).shuffled().firstOrNull()?.let {
                VillageAbility(
                    day = latestVillageDay.day,
                    myselfId = seer.id,
                    targetId = it.id,
                    targetFootstep = null,
                    abilityType = getAbilityType()
                )
            } // 自分しかいない場合はなし
        }
    }

    override fun processDayChangeAction(dayChange: DayChange): DayChange {
        val latestDay = dayChange.village.days.latestDay()
        var messages = dayChange.messages.copy()
        var village = dayChange.village.copy()

        dayChange.village.participant.filterAlive().list.filter {
            it.skill!!.toCdef().isHasDivineAbility
        }.forEach { seer ->
            dayChange.abilities.filterYesterday(village).list.find {
                it.myselfId == seer.id
            }?.let { ability ->
                messages = messages.add(createDivineMessage(dayChange.village, ability, seer))
                // 呪殺対象なら死亡
                if (isDivineKill(dayChange, ability.targetId!!)) village = village.divineKillParticipant(ability.targetId, latestDay)
                // TODO 逆呪殺
            }
        }

        return dayChange.copy(
            messages = messages,
            village = village
        ).setIsChange(dayChange)
    }

    override fun isAvailableNoTarget(): Boolean = false

    override fun isUsable(village: Village, participant: VillageParticipant): Boolean = participant.isAlive() // 生存していたら行使できる

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun createDivineMessage(
        village: Village,
        ability: VillageAbility,
        seer: VillageParticipant
    ): Message {
        val myChara = village.participant.member(ability.myselfId).chara
        val targetChara = village.participant.member(ability.targetId!!).chara
        val isWolf = village.participant.member(ability.targetId).skill!!.toCdef().isDivineResultWolf
        val text = createDivineMessageString(myChara, targetChara, isWolf)
        return Message.createSeerPrivateMessage(text, village.days.latestDay().day, seer)
    }

    private fun createDivineMessageString(chara: Chara, targetChara: Chara, isWolf: Boolean): String =
        "${chara.name.fullName()}は、${targetChara.name.fullName()}を占った。\n${targetChara.name.fullName()}は人狼${if (isWolf) "の" else "ではない"}ようだ。"

    private fun isDivineKill(dayChange: DayChange, targetId: Int): Boolean {
        // 対象が既に死亡していたら呪殺ではない
        if (!dayChange.village.participant.member(targetId).isAlive()) return false
        // 対象が呪殺対象でなければ呪殺ではない
        return dayChange.village.participant.member(targetId).skill!!.toCdef().isDeadByDivine
    }
}
