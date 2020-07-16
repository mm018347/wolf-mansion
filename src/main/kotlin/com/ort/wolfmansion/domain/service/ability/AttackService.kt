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
class AttackService : IAbilityDomainService {

    override fun getAbilityType(): AbilityType = AbilityType(CDef.AbilityType.襲撃)

    override fun getSelectableTargetList(
        village: Village,
        participant: VillageParticipant?,
        villageAbilities: VillageAbilities
    ): List<VillageParticipant> {
        participant ?: return listOf()

        return if (village.days.latestDay().day == 1) {
            // ダミーキャラ固定
            listOf(village.dummyParticipant())
        } else {
            // 襲撃対象に選べる & 生存している
            village.participant.filterAlive().list
                .filter { !it.skill!!.toCdef().isNotSelectableAttack }
        }
    }

    override fun getSelectingTarget(
        village: Village,
        participant: VillageParticipant?,
        villageAbilities: VillageAbilities
    ): VillageParticipant? {
        participant ?: return null

        // 襲撃能力のある参加者のID
        val attackableParticipantIdList =
            village.participant.list.filter { it.skill!!.toCdef().isHasAttackAbility }.map { it.id }

        val targetVillageParticipantId = villageAbilities
            .filterLatestday(village)
            .filterByAbility(getAbilityType())
            .list
            .find { attackableParticipantIdList.contains(it.myselfId) }?.targetId ?: return null

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
        "襲撃者を${myself.name()}に、襲撃対象を${checkNotNull(target?.name())}に、通過する部屋を${footstep ?: "なし"}に設定しました。"

    override fun getDefaultAbilityList(village: Village, villageAbilities: VillageAbilities): List<VillageAbility> {
        // 進行中のみ
        if (!village.status.isProgress()) return listOf()
        // 最新日id
        val latestDay = village.days.latestDay()
        // 襲撃者は生存している人狼からランダムに
        val attackerCandidateList = village.participant.filterAlive().list.filter {
            it.skill!!.toCdef().isHasAttackAbility
        }

        val wolf = if (attackerCandidateList.size > 1 && !village.setting.rules.availableSameWolfAttack) {
            // 連続襲撃なし
            attackerCandidateList.filterNot {
                val yesterdayAttackerId = villageAbilities.filterYesterday(village).filterByAbility(getAbilityType()).list.firstOrNull()?.myselfId
                it.id == yesterdayAttackerId
            }.shuffled().firstOrNull()
        } else {
            // 連続襲撃ありor残りひとり
            attackerCandidateList.shuffled().firstOrNull()
        } ?: return listOf() // 生存している人狼がいないので襲撃なし

        return if (latestDay.day == 1) { // 1日目はダミー固定
            listOf(
                VillageAbility(
                    day = latestDay.day,
                    myselfId = wolf.id,
                    targetId = village.dummyParticipant().id,
                    targetFootstep = null,
                    abilityType = getAbilityType()
                )
            )
        } else { // 2日目以降は生存者からランダム
            val target = village.participant.filterAlive().findRandom {
                !it.skill!!.toCdef().isHasAttackAbility
            } ?: return listOf() // 生存している対象がいないので襲撃なし
            listOf(VillageAbility(
                day = latestDay.day,
                myselfId = wolf.id,
                targetId = target.id,
                targetFootstep = null,
                abilityType = getAbilityType()
            ))
        }
    }

    override fun processDayChangeAction(dayChange: DayChange): DayChange {
        val latestDay = dayChange.village.days.latestDay()
        val aliveWolf = dayChange.village.participant.findRandom {
            it.isAlive() && it.skill!!.toCdef().isHasAttackAbility
        } ?: return dayChange

        var village = dayChange.village.copy()
        var messages = dayChange.messages.copy()

        // 昨日セットした襲撃
        dayChange.abilities.filterYesterday(village).filterByAbility(getAbilityType()).list.find {
            it.targetId != null
        }?.let { ability ->
            // 襲撃メッセージ
            messages = messages.add(createAttackMessage(village, aliveWolf, ability))
            // 襲撃成功したら死亡
            if (isAttackSuccess(dayChange, ability.targetId!!)) village = village.attackParticipant(ability.targetId, latestDay)
        } ?: return dayChange

        return dayChange.copy(
            village = village,
            messages = messages
        ).setIsChange(dayChange)
    }

    // 対象なしを選択できるか
    override fun isAvailableNoTarget(): Boolean = false

    // 能力行使できるか
    override fun isUsable(village: Village, participant: VillageParticipant): Boolean = participant.isAlive() // 生存していたら行使できる

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun isAttackSuccess(dayChange: DayChange, targetId: Int): Boolean {
        // 対象が既に死亡していたら失敗
        if (!dayChange.village.participant.member(targetId).isAlive()) return false
        // 対象が護衛されていたら失敗
        if (isGuarded(dayChange.abilities, dayChange.village, targetId)) return false
        // 対象が襲撃を耐える役職なら失敗
        return !dayChange.village.participant.member(targetId).skill!!.toCdef().isNoDeadByAttack
    }

    // 護衛されたか
    private fun isGuarded(villageAbilities: VillageAbilities, village: Village, targetId: Int): Boolean {
        return villageAbilities
            .filterYesterday(village) // 昨日セットした護衛
            .filterByAbility(AbilityType(CDef.AbilityType.護衛))
            .list.any {
            // 護衛役職が生存していて
            village.participant.member(it.myselfId).isAlive()
            // 襲撃対象が護衛されている
            it.targetId == targetId
        }
    }

    /**
     * 襲撃メッセージ
     * @param village village
     * @param charas charas
     * @param wolf 狼
     * @param ability abilityType
     */
    private fun createAttackMessage(
        village: Village,
        wolf: VillageParticipant,
        ability: VillageAbility
    ): Message {
        val targetChara = village.participant.member(ability.targetId!!).chara
        val text = createAttackMessageString(wolf.chara, targetChara)
        return Message.createAttackPrivateMessage(text, village.days.latestDay().day)
    }

    /**
     * 襲撃メッセージ
     * @param chara 狼
     * @param targetChara 被襲撃者
     */
    private fun createAttackMessageString(chara: Chara, targetChara: Chara): String =
        "${chara.name.fullName()}達は、${targetChara.name.fullName()}を襲撃した。"
}
