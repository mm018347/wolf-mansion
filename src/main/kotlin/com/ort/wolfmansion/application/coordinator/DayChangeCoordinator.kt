package com.ort.wolfmansion.application.coordinator

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.application.service.AbilityService
import com.ort.wolfmansion.application.service.CharachipService
import com.ort.wolfmansion.application.service.CommitService
import com.ort.wolfmansion.application.service.FootstepService
import com.ort.wolfmansion.application.service.MessageService
import com.ort.wolfmansion.application.service.PlayerService
import com.ort.wolfmansion.application.service.VillageService
import com.ort.wolfmansion.application.service.VoteService
import com.ort.wolfmansion.domain.model.charachip.Charas
import com.ort.wolfmansion.domain.model.commit.Commits
import com.ort.wolfmansion.domain.model.daychange.DayChange
import com.ort.wolfmansion.domain.model.message.MessageQuery
import com.ort.wolfmansion.domain.model.player.Players
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.ability.VillageAbilities
import com.ort.wolfmansion.domain.model.village.footstep.VillageFootsteps
import com.ort.wolfmansion.domain.model.village.vote.VillageVotes
import com.ort.wolfmansion.domain.service.daychange.DayChangeService
import com.ort.wolfmansion.fw.exception.WolfMansionBusinessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DayChangeCoordinator(
    private val villageService: VillageService,
    private val voteService: VoteService,
    private val abilityService: AbilityService,
    private val commitService: CommitService,
    private val footstepService: FootstepService,
    private val messageService: MessageService,
    private val charachipService: CharachipService,
    private val playerService: PlayerService,
    // domain service
    private val dayChangeService: DayChangeService
) {

    /**
     * 必要あれば日付更新
     *
     * @param village village
     */
    @Transactional(rollbackFor = [Exception::class, WolfMansionBusinessException::class])
    fun dayChangeIfNeeded(village: Village) {
        val votes: VillageVotes = voteService.findVillageVotes(village.id)
        val abilities: VillageAbilities = abilityService.findVillageAbilities(village.id)
        val footsteps: VillageFootsteps = footstepService.findVillageFootsteps(village.id)
        val commits: Commits = commitService.findCommits(village.id)
        // 最新日の通常発言
        val todayMessages =
            messageService.findMessages(village.id, village.days.latestDay().day, MessageQuery(listOf(CDef.MessageType.通常発言)))
        val charas: Charas = charachipService.findCharas(village.setting.charachip.charachipId)
        val players: Players = playerService.findPlayers(village.id)

        val beforeDayChange = DayChange(
            village.copy(
                participant = village.participant.filterNotGone()
            ), votes, abilities, footsteps, players
        )

        // プロローグで長時間発言していない人を退村させる
        var dayChange: DayChange = dayChangeService.leaveParticipantIfNeeded(
            daychange = beforeDayChange,
            todayMessages = todayMessages,
            charas = charas
        ).let {
            if (it.isChanged) update(beforeDayChange, it)
            it.copy(isChanged = false)
        }

        // 必要あれば日付追加
        dayChange = dayChangeService.addDayIfNeeded(dayChange, commits).let {
            if (!it.isChanged) return
            update(beforeDayChange, it)
            it.copy(isChanged = false)
        }

        // 登録後の村日付idが必要になるので取得し直す
        dayChange = dayChange.copy(village = villageService.findVillage(village.id))

        // 日付更新
        dayChangeService.process(dayChange, todayMessages, charas).let {
            if (it.isChanged) update(dayChange, it)
        }
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun update(before: DayChange, after: DayChange) {
        val villageId = before.village.id
        // player
        if (before.players.existsDifference(after.players)) {
            playerService.updateDifference(before.players, after.players)
        }
        // village
        if (before.village.existsDifference(after.village)) {
            villageService.updateVillageDifference(before.village, after.village)
        }
        // message
        if (before.messages.existsDifference(after.messages)) {
            messageService.updateDifference(villageId, before.messages, after.messages)
        }
        // votes
        if (before.votes.existsDifference(after.votes)) {
            voteService.updateDifference(villageId, before.votes, after.votes)
        }
        // abilities
        if (before.abilities.existsDifference(after.abilities)) {
            abilityService.updateDifference(villageId, before.abilities, after.abilities)
        }
    }
}