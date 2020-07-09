package com.ort.wolfmansion.domain.model.village

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.domain.model.camp.Camp
import com.ort.wolfmansion.domain.model.charachip.Chara
import com.ort.wolfmansion.domain.model.commit.Commits
import com.ort.wolfmansion.domain.model.message.Message
import com.ort.wolfmansion.domain.model.message.MessageContent
import com.ort.wolfmansion.domain.model.player.Player
import com.ort.wolfmansion.domain.model.skill.Skill
import com.ort.wolfmansion.domain.model.skill.SkillRequest
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipants
import com.ort.wolfmansion.domain.model.village.room.VillageRooms
import com.ort.wolfmansion.domain.model.village.vote.VillageVotes
import com.ort.wolfmansion.fw.exception.WolfMansionBusinessException

data class Village(
    val id: Int,
    val name: String,
    val creatorPlayerName: String,
    val status: VillageStatus,
    val rooms: VillageRooms?,
    val winCamp: Camp?,
    val setting: VillageSettings,
    val participant: VillageParticipants,
    val spectator: VillageParticipants,
    val days: VillageDays
) {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private val initialMessage: String =
        "昼間は人間のふりをして、夜に正体を現すという人狼。" +
            "\nその人狼が、この館に紛れ込んでいるという噂が広がった。" +
            "\n\n村人達は半信半疑ながらも、館の大広間に集められることになった。"

    private val day1Message: String =
        "さあ、自らの姿を鏡に映してみよう。\nそこに映るのはただの村人か、それとも血に飢えた人狼か。\n\nたとえ人狼でも、多人数で立ち向かえば怖くはない。\n問題は、だれが人狼なのかという事だ。\n占い師の能力を持つ人間ならば、それを見破れるだろう。"

    private val day2Message: String =
        "ついに犠牲者が出た。\n\n村人達は、この中にいる人狼を排除するため、投票を行う事にした。\n無実の犠牲者が出るのもやむをえない。村が全滅するよりは……。\n\n最後まで残るのは村人か、それとも人狼か。"

    private val cancelMessage: String = "人数が不足しているため廃村しました。"

    // ===================================================================================
    //                                                                             message
    //                                                                           =========
    /** 村作成時のメッセージ */
    fun createVillagePrologueMessage(): Message =
        Message.createPublicSystemMessage(initialMessage, days.latestDay().day)

    /** 1日目のメッセージ */
    fun createVillageDay1Message(): Message =
        Message.createPublicSystemMessage(day1Message, days.latestDay().day)

    /** 2日目のメッセージ */
    fun createVillageDay2Message(): Message =
        Message.createPublicSystemMessage(day2Message, days.latestDay().day)

    /** 廃村メッセージ */
    fun createCancelVillageMessage(): Message =
        Message.createPublicSystemMessage(cancelMessage, days.latestDay().day)

    /** 構成メッセージ */
    fun createOrganizationMessage(): Message {
        val skillCountMap = setting.organizations.mapToSkillCount(participant.count)
        val text = CDef.Skill.listAll().sortedBy { Integer.parseInt(it.order()) }.mapNotNull { cdefSkill ->
            val skill = Skill(cdefSkill)
            val count = skillCountMap[cdefSkill]
            if (count == null || count == 0) null else "${skill.name}が${count}人"
        }.joinToString(
            separator = "、\n",
            prefix = "この村には\n",
            postfix = "\nいるようだ。"
        )
        return Message.createPublicSystemMessage(text, days.latestDay().day)
    }

    /** ダミーキャラの1日目発言 */
    fun createDummyCharaFirstDayMessage(): Message? {
        val firstDayMessage = dummyParticipant().chara.defaultMessage.firstDayMessage ?: return null
        val messageContent = MessageContent(
            messageType = CDef.MessageType.通常発言,
            text = firstDayMessage,
            convertDisable = true, // TODO
            faceType = CDef.FaceType.通常
        )
        return Message.createSayMessage(
            from = dummyParticipant(),
            day = days.latestDay().day,
            messageContent = messageContent
        )
    }

    /** ステータスに応じたメッセージ */
    fun createStatusMessage(isParticipating: Boolean): String {
        return status.createStatusMessage(
            capacity = setting.capacity,
            latestDay = days.latestDay(),
            winCamp = winCamp,
            isParticipating = isParticipating
        )
    }

    /** コミット状況 */
    fun createCommitMessage(commits: Commits): String? {
        if (!setting.rules.availableCommit || !status.isProgress()) return null
        val commitCount = commits.list.count { it.day == days.latestDay().day }
        val aliveCount = this.notDummyParticipant().filterAlive().filterNotGone().count
        return "生存者全員がコミットすると日付が更新されます。\n\n" +
            "現在 $commitCount/${aliveCount}人 がコミットしています。"
    }

    /** 突然死候補状況 */
    fun createSuddenlyDeathMessage(votes: VillageVotes): String? {
        if (!setting.rules.availableSuddenlyDeath || !status.isProgress()) return null
        val voteParticipantIdList = votes.list
            .filter { it.day == days.latestDay().day }
            .map { it.myselfId }
        val noVoteParticipantNameList = notDummyParticipant().filterNotGone().filterAlive().list.filterNot {
            voteParticipantIdList.contains(it.id)
        }.map { it.name() }
        if (noVoteParticipantNameList.isEmpty()) return null
        return "本日まだ投票していない者は、${noVoteParticipantNameList.joinToString(separator = "、")}" +
            "\n\n※未投票で更新時刻を迎えると突然死します。"
    }

    // ===================================================================================
    //                                                                                read
    //                                                                           =========
    fun dummyParticipant(): VillageParticipant = participant.memberByCharaId(setting.charachip.dummyCharaId)

    fun findPersonByParticipantId(participantId: Int): VillageParticipant? {
        participant.findMember(participantId)?.let { return it }
        return spectator.findMember(participantId)?.let { it }
    }

    fun notDummyParticipant(): VillageParticipants {
        val notDummyMembers = participant.list.filter { it.chara.id != setting.charachip.dummyCharaId }
        return VillageParticipants(
            count = notDummyMembers.size,
            list = notDummyMembers
        )
    }

    fun todayDeadParticipants(): VillageParticipants {
        val deadTodayMemberList = participant.filterAlive().list.filter {
            it.dead?.villageDay?.day == days.latestDay().day
        }
        return VillageParticipants(
            count = deadTodayMemberList.size,
            list = deadTodayMemberList
        )
    }

    fun existsDifference(village: Village): Boolean {
        return status.code != village.status.code
            || winCamp?.code != village.winCamp?.code
            || participant.existsDifference(village.participant)
            || days.existsDifference(village.days)
            || setting.existsDifference(village.setting)
    }

    // ===================================================================================
    //                                                                                 権限
    //                                                                           =========
    // 参加可能か
    fun isAvailableParticipate(): Boolean {
        // プロローグでない
        if (!status.isPrologue()) return false
        // 既に最大人数まで参加している
        if (participant.count >= setting.capacity.max) return false
        return true
    }

    // 参加チェック
    fun assertParticipate(charaId: Int, password: String?) {
        // 既に参加しているキャラはNG
        if (isAlreadyParticipateCharacter(charaId)) throw WolfMansionBusinessException("既に参加されているキャラクターです")
        // パスワードが合っているかチェック
        assertPassword(password)
    }

    // 見学可能か
    fun isAvailableSpectate(charachipCharaNum: Int): Boolean {
        // プロローグでない
        if (!status.isPrologue()) return false
        // 既に最大人数まで参加している
        if (charachipCharaNum - setting.capacity.max <= spectator.count) return false
        // 見学できない設定の村である
        if (!setting.rules.availableSpectate) return false
        return true
    }

    // 退村可能か
    fun isAvailableLeave(): Boolean = status.isPrologue() // プロローグなら退村できる

    // 役職希望可能か
    fun isAvailableSkillRequest(): Boolean = status.isPrologue() && setting.rules.availableSkillRequest

    // 役職希望変更チェック
    fun assertSkillRequest(first: CDef.Skill, second: CDef.Skill) {
        if (setting.organizations.allRequestableSkillList().none { it.code == first.code() }) throw WolfMansionBusinessException("役職希望変更できません")
        if (setting.organizations.allRequestableSkillList().none { it.code == second.code() }) throw WolfMansionBusinessException("役職希望変更できません")
    }

    // コミット可能か
    fun isAvailableCommit(): Boolean = status.isProgress() && setting.rules.availableCommit

    // 能力を行使できるか
    fun canUseAbility(): Boolean = status.isProgress()

    // 投票できるか
    fun isAvailableVote(): Boolean = status.isProgress() && days.latestDay().day > 1 // 2日目から

    // 囁き発言を見られるか
    fun isViewableWerewolfSay(): Boolean = status.isSolved()

    // 墓下発言を見られるか
    fun isViewableGraveSay(): Boolean = status.isSolved() || setting.rules.visibleGraveMessage

    // 独り言を見られるか
    fun isViewableMonologueSay(): Boolean = status.isSolved() // 終了していたら全て見られる

    // 見学発言を見られるか
    fun isViewableSpectateSay(): Boolean {
        // 進行中以外は開放
        if (!status.isProgress()) return true
        // 見られる設定なら開放
        return setting.rules.visibleGraveMessage
    }

    // 村として白黒霊能結果を見られるか
    fun isViewablePsychicMessage(): Boolean = status.isSolved()// 終了していたら全て見られる

    // 村として秘話を見られるか
    fun isViewableSecretSay(): Boolean = status.isSolved()

    // 村として襲撃メッセージを見られるか
    fun isViewableAttackMessage(): Boolean = status.isSolved() // 終了していたら全て見られる

    // 発言可能か
    fun isAvailableSay(): Boolean = !status.isFinishedVillage() // 終了していたら不可

    // 通常発言可能か
    fun isSayableNormalSay(): Boolean = !status.isFinishedVillage() // 終了していたら不可

    // 囁き可能か
    fun isSayableWerewolfSay(): Boolean = status.isProgress() // 進行中以外は不可

    // 墓下発言可能か
    fun isSayableGraveSay(): Boolean = status.isProgress() // 進行中以外は不可

    // 独り言発言可能か
    fun isSayableMonologueSay(): Boolean = true // 制約なし

    // 村として見学発言できるか
    fun isSayableSpectateSay(): Boolean = true // 制約なし

    fun assertMessageRestrict(messageContent: MessageContent, latestDayMessageList: List<Message>) {
        val restrict = setting.rules.messageRestrict.restrict(messageContent.type.toCdef()) ?: return // 制限なし
        restrict.assertSay(messageContent, status, latestDayMessageList)
    }


    // ===================================================================================
    //                                                                              update
    //                                                                        ============
    // 入村
    fun participate(
        player: Player,
        chara: Chara,
        firstRequestSkill: CDef.Skill = CDef.Skill.おまかせ,
        secondRequestSkill: CDef.Skill = CDef.Skill.おまかせ
    ): Village {
        return this.copy(
            participant = participant.addParticipant(
                chara = chara,
                player = player,
                skillRequest = SkillRequest(Skill(firstRequestSkill), Skill(secondRequestSkill)),
                isSpectator = false
            )
        )
    }

    // 見学
    fun spectate(
        player: Player,
        chara: Chara
    ): Village {
        return this.copy(
            spectator = spectator.addParticipant(
                chara = chara,
                player = player,
                skillRequest = SkillRequest(Skill(CDef.Skill.おまかせ), Skill(CDef.Skill.おまかせ)),
                isSpectator = true
            )
        )
    }

    // 希望役職変更
    fun changeSkillRequest(participantId: Int, first: CDef.Skill, second: CDef.Skill): Village =
        this.copy(participant = participant.changeSkillRequest(participantId, first, second))

    // 退村
    fun leaveParticipant(participantId: Int): Village = this.copy(participant = this.participant.leave(participantId))

    // 突然死
    fun suddenlyDeathParticipant(participantId: Int, latestDay: VillageDay): Village =
        this.copy(participant = this.participant.suddenlyDeath(participantId, latestDay))

    // 処刑
    fun executeParticipant(participantId: Int, latestDay: VillageDay): Village =
        this.copy(participant = this.participant.execute(participantId, latestDay))

    // 襲撃
    fun attackParticipant(participantId: Int, latestDay: VillageDay): Village =
        this.copy(participant = this.participant.attack(participantId, latestDay))

    // 呪殺
    fun divineKillParticipant(participantId: Int, latestDay: VillageDay): Village =
        this.copy(participant = this.participant.divineKill(participantId, latestDay))

    // 役職割り当て
    fun assignSkill(assignedParticipants: VillageParticipants): Village {
        return this.copy(participant = assignedParticipants)
    }

    // ステータス変更
    fun changeStatus(cdefVillageStatus: CDef.VillageStatus): Village = this.copy(status = VillageStatus(cdefVillageStatus))

    // 勝利陣営設定
    fun win(winCamp: CDef.Camp): Village {
        return this.copy(
            winCamp = Camp(winCamp), // 村自体の勝利陣営
            participant = this.participant.winLose(winCamp) // 個人ごとの勝敗
        )
    }

    // 最新の村日付を追加
    fun addNewDay(): Village {
        val dayList = mutableListOf<VillageDay>()
        dayList.addAll(days.list)
        val newDay = VillageDay(
            day = days.latestDay().day + 1, // 一旦長期だけを考えるので常に昼
            dayChangeDatetime = days.latestDay().dayChangeDatetime.plusSeconds(setting.time.dayChangeIntervalSeconds.toLong())
        )
        dayList.add(newDay)
        return this.copy(days = this.days.copy(list = dayList))
    }

    // 最新の日を24時間にする
    fun extendLatestDay(): Village = this.copy(days = this.days.extendLatestDay())

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    private fun isAlreadyParticipateCharacter(charaId: Int): Boolean {
        return participant.list.any { it.chara.id == charaId }
            || spectator.list.any { it.chara.id == charaId }
    }

    private fun assertPassword(password: String?) {
        setting.password.joinPassword ?: return
        if (setting.password.joinPassword != password) throw WolfMansionBusinessException("入村パスワードが誤っています")
    }
}