package com.ort.wolfmansion.application.coordinator

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.application.service.AbilityService
import com.ort.wolfmansion.application.service.CharachipService
import com.ort.wolfmansion.application.service.CommitService
import com.ort.wolfmansion.application.service.MessageService
import com.ort.wolfmansion.application.service.PlayerService
import com.ort.wolfmansion.application.service.VillageService
import com.ort.wolfmansion.application.service.VoteService
import com.ort.wolfmansion.domain.model.ability.AbilityType
import com.ort.wolfmansion.domain.model.charachip.Chara
import com.ort.wolfmansion.domain.model.charachip.Charas
import com.ort.wolfmansion.domain.model.commit.Commit
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.message.MessageContent
import com.ort.wolfmansion.domain.model.myself.SituationAsParticipant
import com.ort.wolfmansion.domain.model.player.Player
import com.ort.wolfmansion.domain.model.player.Players
import com.ort.wolfmansion.domain.model.skill.SkillRequest
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.ability.VillageAbilities
import com.ort.wolfmansion.domain.model.village.ability.VillageAbility
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.domain.model.village.vote.VillageVote
import com.ort.wolfmansion.domain.model.village.vote.VillageVotes
import com.ort.wolfmansion.domain.service.say.SayService
import com.ort.wolfmansion.domain.service.skill.SkillRequestService
import com.ort.wolfmansion.domain.service.village.participate.ParticipateService
import com.ort.wolfmansion.domain.service.village.settings.SettingsService
import com.ort.wolfmansion.fw.exception.WolfMansionBusinessException
import com.ort.wolfmansion.fw.security.WolfMansionUser
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class VillageCoordinator(
    // application service
    private val villageService: VillageService,
    private val playerService: PlayerService,
    private val messageService: MessageService,
    private val charachipService: CharachipService,
    private val abilityService: AbilityService,
    private val voteService: VoteService,
    private val commitService: CommitService,
    private val dayChangeCoordinator: DayChangeCoordinator,
    // domain service
    private val participateService: ParticipateService,
    private val sayService: SayService,
    private val skillRequestService: SkillRequestService,
    private val abilityDomainService: com.ort.wolfmansion.domain.service.ability.AbilityDomainService,
    private val voteDomainService: com.ort.wolfmansion.domain.service.vote.VoteDomainService,
    private val commitDomainService: com.ort.wolfmansion.domain.service.commit.CommitDomainService,
    private val settingService: SettingsService
) {

    /**
     * 村参加者取得
     */
    fun findParticipant(village: Village, user: WolfMansionUser?): VillageParticipant? {
        user ?: return null
        val player: Player = playerService.findPlayer(user)
        return this.findParticipant(village, player.id)
    }

    /**
     * 村参加者取得
     */
    fun findParticipant(village: Village, playerId: Int): VillageParticipant? {
        val participant: VillageParticipant? = village.participant.list.find { it.player.id == playerId && !it.isGone }
        return participant ?: village.spectator.list.find { it.player.id == playerId && !it.isGone }
    }

    /**
     * 村登録
     */
    @Transactional(rollbackFor = [Exception::class, WolfMansionBusinessException::class])
    fun registerVillage(paramVillage: Village, user: WolfMansionUser): Int {
        // 作成できない状況ならエラー
        val player: Player = playerService.findPlayer(user)
        player.assertCreateVillage(user)
        // 村を登録
        val village: Village = registerVillage(paramVillage)

        return village.id
    }

    /**
     * 村に参加できるかチェック
     */
    fun assertParticipate(
        villageId: Int,
        user: WolfMansionUser,
        charaId: Int,
        message: String,
        isSpectate: Boolean,
        firstRequestSkill: CDef.Skill = CDef.Skill.おまかせ,
        secondRequestSkill: CDef.Skill = CDef.Skill.おまかせ,
        password: String?
    ) {
        // 参加できない状況ならエラー
        val village: Village = villageService.findVillage(villageId)
        val player: Player = playerService.findPlayer(user)
        val charas: Charas = charachipService.findCharas(village.setting.charachip.charachipId)

        if (isSpectate) {
            participateService.assertSpectate(
                player,
                village,
                charaId,
                charas.list.size,
                password
            )
        } else {
            participateService.assertParticipate(
                player,
                village,
                charaId,
                firstRequestSkill,
                secondRequestSkill,
                isSpectate,
                password
            )
        }
        // 参加発言
        val messageContent = MessageContent(
            CDef.MessageType.通常発言,
            message,
            true, // TODO
            CDef.FaceType.通常
        )
        val chara = charas.chara(charaId)
        sayService.assertParticipateSay(village, chara, messageContent)
    }

    /**
     * 村に参加
     */
    @Transactional(rollbackFor = [Exception::class, WolfMansionBusinessException::class])
    fun participate(
        villageId: Int,
        player: Player,
        chara: Chara,
        message: String,
        isSpectate: Boolean,
        firstRequestSkill: CDef.Skill = CDef.Skill.おまかせ,
        secondRequestSkill: CDef.Skill = CDef.Skill.おまかせ
    ) {
        // 村参加者登録
        var village: Village = villageService.findVillage(villageId)
        val changedVillage: Village = if (isSpectate) {
            village.spectate(
                player = player,
                chara = chara
            )
        } else {
            village.participate(
                player = player,
                chara = chara,
                firstRequestSkill = firstRequestSkill,
                secondRequestSkill = secondRequestSkill
            )
        }
        village = villageService.updateVillageDifference(village, changedVillage)
        val participant: VillageParticipant = findParticipant(village, player.id)!!
        // {N}人目、{キャラ名} とユーザー入力の発言
        messageService.registerParticipateMessage(
            village = village,
            participant = village.participant.member(participant.id),
            chara = chara,
            message = message,
            isSpectate = isSpectate
        )
    }

    /**
     * 役職希望変更
     */
    fun changeSkillRequest(villageId: Int, user: WolfMansionUser, firstRequestSkill: String, secondRequestSkill: String) {
        // 役職希望変更できない状況ならエラー
        val village: Village = villageService.findVillage(villageId)
        val participant: VillageParticipant? = findParticipant(village, user)
        skillRequestService.assertSkillRequest(village, participant, firstRequestSkill, secondRequestSkill)
        // 役職希望変更
        val changedVillage: Village = village.changeSkillRequest(
            participant!!.id,
            CDef.Skill.codeOf(firstRequestSkill)!!,
            CDef.Skill.codeOf(secondRequestSkill)!!
        )
        villageService.updateVillageDifference(village, changedVillage)
    }

    /**
     * 退村
     */
    @Transactional(rollbackFor = [Exception::class, WolfMansionBusinessException::class])
    fun leave(villageId: Int, user: WolfMansionUser) {
        // 退村できない状況ならエラー
        val village: Village = villageService.findVillage(villageId)
        val participant: VillageParticipant? = findParticipant(village, user)
        participateService.assertLeave(village, participant)
        // 退村
        val updatedVillage: Village = villageService.updateVillageDifference(
            village,
            village.leaveParticipant(participant!!.id)
        )
        // 退村メッセージ
        messageService.registerLeaveMessage(updatedVillage, participant.chara)
    }

    /**
     * 発言できるか確認
     */
    fun confirmToSay(villageId: Int, user: WolfMansionUser, messageText: String, messageType: String, faceType: String) {
        val messageContent: MessageContent = MessageContent(
            CDef.MessageType.codeOf(messageType),
            messageText,
            false, // TODO
            CDef.FaceType.codeOf(faceType)
        )
        // 発言できない状況ならエラー
        assertSay(villageId, user, messageContent)
    }

    /**
     * 発言
     */
    @Transactional(rollbackFor = [Exception::class, WolfMansionBusinessException::class])
    fun say(villageId: Int, user: WolfMansionUser, messageText: String, messageType: String, faceType: String) {
        val messageContent: MessageContent = MessageContent(
            CDef.MessageType.codeOf(messageType),
            messageText,
            false, // TODO
            CDef.FaceType.codeOf(faceType)
        )
        // 発言できない状況ならエラー
        assertSay(villageId, user, messageContent)
        // 発言
        val village: Village = villageService.findVillage(villageId)
        val participant: VillageParticipant = findParticipant(village, user)!!
        val message: Message = Message.createSayMessage(
            from = participant,
            day = village.days.latestDay().day,
            messageContent = messageContent
        )
        messageService.registerSayMessage(villageId, message)
    }

    /**
     * 能力セット
     */
    @Transactional(rollbackFor = [Exception::class, WolfMansionBusinessException::class])
    fun setAbility(villageId: Int, user: WolfMansionUser, targetId: Int?, abilityTypeCode: String) {
        // 能力セットできない状況ならエラー
        val village: Village = villageService.findVillage(villageId)
        val participant: VillageParticipant? = findParticipant(village, user)
        val abilityType = AbilityType(CDef.AbilityType.codeOf(abilityTypeCode))
        abilityDomainService.assertAbility(village, participant, abilityType, targetId)
        // 能力セット
        val villageAbility = VillageAbility(
            village.days.latestDay().day,
            participant!!.id,
            targetId,
            null, // TODO 足音
            abilityType
        )
        abilityService.updateAbility(village.id, villageAbility)
        val charas: Charas = charachipService.findCharas(village.setting.charachip.charachipId)
        messageService.registerAbilitySetMessage(village, participant, targetId, abilityType, charas)
    }

    /**
     * 投票セット
     *
     * @param villageId villageId
     * @param user user
     * @param targetId 対象村参加者ID
     */
    @Transactional(rollbackFor = [Exception::class, WolfMansionBusinessException::class])
    fun setVote(villageId: Int, user: WolfMansionUser, targetId: Int) {
        // 投票セットできない状況ならエラー
        val village: Village = villageService.findVillage(villageId)
        val participant: VillageParticipant? = findParticipant(village, user)
        voteDomainService.assertVote(village, participant, targetId)
        // 投票
        val villageVote = VillageVote(
            village.days.latestDay().day,
            participant!!.id,
            targetId
        )
        voteService.updateVote(village.id, villageVote)
    }

    /**
     * コミットセット
     *
     * @param villageId villageId
     * @param user user
     * @param doCommit コミットするか
     */
    @Transactional(rollbackFor = [Exception::class, WolfMansionBusinessException::class])
    fun setCommit(villageId: Int, user: WolfMansionUser, doCommit: Boolean) {
        // コミットできない状況ならエラー
        val village: Village = villageService.findVillage(villageId)
        val participant: VillageParticipant? = findParticipant(village, user)
        commitDomainService.assertCommit(village, participant)
        // コミット
        val commit = Commit(village.days.latestDay().day, participant!!.id, doCommit)
        commitService.updateCommit(village.id, commit)
        messageService.registerCommitMessage(village, participant.chara, doCommit)
        // 日付更新
        if (doCommit) dayChangeCoordinator.dayChangeIfNeeded(village)
    }

    fun findActionSituation(
        village: Village,
        user: WolfMansionUser?,
        players: Players,
        charas: Charas
    ): SituationAsParticipant {
        val player: Player? = user?.let { playerService.findPlayer(it) }
        val participant: VillageParticipant? = findParticipant(village, user)
        val skillRequest: SkillRequest? = participant?.let { village.participant.member(it.id).skillRequest }
        val abilities: VillageAbilities = abilityService.findVillageAbilities(village.id)
        val votes: VillageVotes = voteService.findVillageVotes(village.id)
        val commit: Commit? = commitService.findCommit(village, participant)
        val latestDayMessageList: List<Message> =
            messageService.findParticipateDayMessageList(village.id, village.days.latestDay(), participant)

        return SituationAsParticipant(
            myself = participant,
            participate = participateService.convertToSituation(participant, player, village, charas),
            skillRequest = skillRequestService.convertToSituation(village, participant, skillRequest),
            commit = commitDomainService.convertToSituation(village, participant, commit),
            say = sayService.convertToSituation(village, participant, latestDayMessageList, charas),
            ability = abilityDomainService.convertToSituation(participant, village, abilities),
            vote = voteDomainService.convertToSituation(village, participant, votes),
            creator = settingService.convertToSituation(village, participant),
            viewableSpoilerContent = village.isAvailableSpoilerContent() || participant?.isViewableSpoilerContent(village) ?: false
        )
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun registerVillage(paramVillage: Village): Village {
        // 村を登録
        val village: Village = villageService.registerVillage(paramVillage)
        // 村作成時のシステムメッセージを登録
        messageService.registerInitialMessage(village)
        // ダミーキャラを参加させる
        val chara: Chara = charachipService.findChara(village.setting.charachip.dummyCharaId)
        participateDummyChara(village.id, village, chara)

        return village
    }

    private fun participateDummyChara(villageId: Int, village: Village, dummyChara: Chara) {
        val player = playerService.findPlayer(1) // 固定
        val chara = charachipService.findChara(village.setting.charachip.dummyCharaId)
        val message: String = dummyChara.defaultMessage.joinMessage ?: "人狼なんているわけないじゃん。みんな大げさだなあ"
        this.participate(
            villageId = villageId,
            player = player,
            chara = chara,
            message = message,
            isSpectate = false
        )
    }

    private fun assertSay(villageId: Int, user: WolfMansionUser?, messageContent: MessageContent) {
        val village: Village = villageService.findVillage(villageId)
        val participant: VillageParticipant? = findParticipant(village, user)
        val latestDayMessageList: List<Message> =
            messageService.findParticipateDayMessageList(villageId, village.days.latestDay(), participant)
        sayService.assertSay(village, participant, participant?.chara, latestDayMessageList, messageContent)
    }
}