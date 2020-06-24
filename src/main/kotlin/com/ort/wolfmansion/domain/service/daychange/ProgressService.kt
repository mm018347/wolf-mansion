package com.ort.wolfmansion.domain.service.daychange

import com.ort.wolfmansion.domain.model.charachip.Charas
import com.ort.wolfmansion.domain.model.commit.Commits
import com.ort.wolfmansion.domain.model.daychange.DayChange
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.message.Messages
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.service.ability.AbilityDomainService
import com.ort.wolfmansion.domain.service.ability.AttackService
import com.ort.wolfmansion.domain.service.ability.DivineService
import com.ort.wolfmansion.domain.service.ability.GuardService
import com.ort.wolfmansion.domain.service.ability.PsychicService
import com.ort.wolfmansion.domain.service.vote.VoteDomainService
import com.ort.wolfmansion.util.WolfMansionDateUtil
import org.springframework.stereotype.Service

@Service
class ProgressService(
    private val abilityDomainService: AbilityDomainService,
    private val voteDomainService: VoteDomainService,
    private val suddenlyDeathService: SuddenlyDeathService,
    private val executeService: ExecuteService,
    private val psychicService: PsychicService,
    private val divineService: DivineService,
    private val guardService: GuardService,
    private val attackDomainService: AttackService,
    private val miserableDeathService: MiserableDeathService,
    private val epilogueService: EpilogueService
) {

    // ===================================================================================
    //                                                                             Execute
    //                                                                           =========
    fun addDayIfNeeded(dayChange: DayChange, commits: Commits): DayChange {
        // 日付更新の必要がなかったら終了
        if (!shouldForward(dayChange.village, commits)) return dayChange
        // 日付追加
        return addNewDay(dayChange).setIsChange(dayChange)
    }

    fun dayChange(
        beforeDayChange: DayChange,
        todayMessages: Messages,
        charas: Charas
    ): DayChange {
        // 突然死
        var dayChange = suddenlyDeathService.process(beforeDayChange, todayMessages, charas)

        // 処刑
        dayChange = executeService.process(dayChange, charas)

        // 霊能
        dayChange = psychicService.process(dayChange, charas)

        // 占い
        dayChange = divineService.process(dayChange, charas)

        // 護衛
        dayChange = guardService.process(dayChange, charas)

        // 襲撃
        dayChange = attackDomainService.process(dayChange, charas)

        // 無惨メッセージ
        dayChange = miserableDeathService.process(dayChange, charas)

        // 2日目限定メッセージ
        dayChange = addDay2MessageIfNeeded(dayChange)

        // 勝敗
        dayChange = epilogueService.transitionToEpilogueIfNeeded(dayChange)

        // 勝敗が決していたらここで終了
        if (dayChange.village.status.isSolved()) return dayChange.setIsChange(beforeDayChange)

        // 投票や能力行使のデフォルト設定
        dayChange = addDefaultVoteAndAbilities(dayChange)

        // 生存者メッセージ登録
        dayChange = addAliveMemberMessage(dayChange, charas)

        return dayChange.setIsChange(beforeDayChange)
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    // 日付を進める必要があるか
    private fun shouldForward(village: Village, commits: Commits): Boolean {
        // 全員コミットしている
        if (allCommitted(village, commits)) return true
        // 更新日時を過ぎている
        return !WolfMansionDateUtil.currentLocalDateTime().isBefore(village.days.latestDay().dayChangeDatetime)
    }

    private fun allCommitted(village: Village, commits: Commits): Boolean {
        if (!village.setting.rules.availableCommit) return false
        // ダミーを除く最新日の生存者数
        val livingPersonCount = village.notDummyParticipant().filterAlive().count
        // コミット数
        val commitCount = commits.list.filter { it.day == village.days.latestDay().day && it.isCommitting }.size

        return livingPersonCount == commitCount
    }

    // 日付追加
    private fun addNewDay(dayChange: DayChange): DayChange {
        return dayChange.copy(
            village = dayChange.village.addNewDay()
        ).setIsChange(dayChange)
    }

    private fun addDay2MessageIfNeeded(dayChange: DayChange): DayChange {
        val village = dayChange.village
        if (village.days.latestDay().day != 2) return dayChange
        return dayChange.copy(
            messages = dayChange.messages.add(village.createVillageDay2Message())
        ).setIsChange(dayChange)
    }

    // 生存者メッセージ
    private fun addAliveMemberMessage(dayChange: DayChange, charas: Charas): DayChange {
        return dayChange.copy(
            messages = dayChange.messages.add(createAliveMemberMessage(dayChange.village, charas))
        ).setIsChange(dayChange)
    }

    private fun createAliveMemberMessage(village: Village, charas: Charas): Message {
        val text = village.participant.filterAlive().list.joinToString(
            separator = "\n",
            prefix = "現在の生存者は以下の${village.participant.filterAlive().count}名。\n"
        ) { member ->
            charas.chara(member.charaId).name.fullName()
        }
        return Message.createPublicSystemMessage(text, village.days.latestDay().day)
    }

    // デフォルト投票能力行使
    private fun addDefaultVoteAndAbilities(dayChange: DayChange): DayChange {
        val abilities = dayChange.abilities.add(abilityDomainService.createDefaultAbilities(village = dayChange.village))
        val votes = dayChange.votes.add(voteDomainService.createDefaultVotes(village = dayChange.village))
        return dayChange.copy(
            abilities = abilities,
            votes = votes
        ).setIsChange(dayChange)
    }
}
