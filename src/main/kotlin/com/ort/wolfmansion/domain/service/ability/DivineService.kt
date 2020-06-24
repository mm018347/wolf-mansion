package com.ort.wolfmansion.domain.service.ability

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.ability.AbilityType
import com.ort.wolfmansion.domain.model.charachip.Chara
import com.ort.wolfmansion.domain.model.charachip.Charas
import com.ort.wolfmansion.domain.model.daychange.DayChange
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.ability.VillageAbilities
import com.ort.wolfmansion.domain.model.village.ability.VillageAbility
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import org.springframework.stereotype.Service

@Service
class DivineService {

    private val abilityType = AbilityType(CDef.AbilityType.占い)

    fun getSelectableTargetList(
        village: Village,
        participant: VillageParticipant?
    ): List<VillageParticipant> {
        participant ?: return listOf()

        // 自分以外の生存者全員
        return village.participant.filterAlive().list.filter { it.id != participant.id }
    }

    fun getSelectingTarget(village: Village, participant: VillageParticipant?, villageAbilities: VillageAbilities): VillageParticipant? {
        participant ?: return null

        val targetVillageParticipantId = villageAbilities
            .filterLatestday(village)
            .filterByAbility(abilityType)
            .list
            .find { it.myselfId == participant.id }?.targetId ?: return null

        return village.participant.member(targetVillageParticipantId)
    }

    fun createSetMessage(myChara: Chara, targetChara: Chara?): String =
        "${myChara.name.fullName()}が占い対象を${targetChara?.name?.fullName() ?: "なし"}に設定しました。"

    fun getDefaultAbilityList(village: Village): List<VillageAbility> {
        // 進行中のみ
        if (!village.status.isProgress()) return listOf()
        // 最新日id
        val latestVillageDay = village.days.latestDay()
        // 生存している占い能力持ちごとに
        return village.participant.filterAlive().list.filter {
            it.skill!!.toCdef().isHasDivineAbility
        }.mapNotNull { seer ->
            // 対象は自分以外の生存者からランダム
            village.participant
                .filterAlive()
                .findRandom { it.id != seer.id }?.let {
                    VillageAbility(
                        day = latestVillageDay.day,
                        myselfId = seer.id,
                        targetId = it.id,
                        targetFootstep = null,
                        abilityType = abilityType
                    )
                }
        }
    }

    fun process(dayChange: DayChange, charas: Charas): DayChange {
        val latestDay = dayChange.village.days.latestDay()
        var messages = dayChange.messages.copy()
        var village = dayChange.village.copy()

        dayChange.village.participant.filterAlive().list.filter {
            it.skill!!.toCdef().isHasDivineAbility
        }.forEach { seer ->
            dayChange.abilities.filterYesterday(village).list.find {
                it.myselfId == seer.id
            }?.let { ability ->
                messages = messages.add(createDivineMessage(dayChange.village, charas, ability, seer))
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

    fun isAvailableNoTarget(): Boolean = false

    fun isUsable(participant: VillageParticipant): Boolean = participant.isAlive() // 生存していたら行使できる

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun createDivineMessage(
        village: Village,
        charas: Charas,
        ability: VillageAbility,
        seer: VillageParticipant
    ): Message {
        val myChara = charas.chara(village.participant, ability.myselfId)
        val targetChara = charas.chara(village.participant, ability.targetId!!)
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
