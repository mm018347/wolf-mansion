package com.ort.wolfmansion.api.view.model.village

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.api.view.domain.model.myself.SituationAsParticipantView
import com.ort.wolfmansion.api.view.domain.model.village.VillageView
import com.ort.wolfmansion.api.view.domain.model.village.participant.VillageParticipantView
import com.ort.wolfmansion.domain.model.ability.AbilityType
import com.ort.wolfmansion.domain.model.myself.SituationAsParticipant
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.ability.VillageAbilities
import com.ort.wolfmansion.domain.model.village.footstep.VillageFootsteps
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipants
import com.ort.wolfmansion.domain.model.village.vote.VillageVotes
import java.time.LocalDateTime

data class VillageModel(
    /** 村 */
    val village: VillageView,
    /** 何日めか */
    val day: Int,
    /** 次回更新日時 */
    val dayChangeDatetime: LocalDateTime?,
    /** 参加情報 */
    val participateSituation: SituationAsParticipantView,
    /** ネタバレ切り替えを表示するか */
    val dispSpoilerSwitch: Boolean,
    /** ランダムキーワード(カンマ区切り) */
    val randomKeywords: String,

    // 状況欄
    /** 全体状況 */
    val actionSituationList: VillageDaySituationsModel,
    /** 投票 */
    val votes: VillageVotesModel,
    /** 足音 */
    val footsteps: VillageDayFootstepsModel
) {
    constructor(
        village: Village,
        day: Int,
        situation: SituationAsParticipant,
        randomKeywordList: List<String>,
        abilities: VillageAbilities,
        votes: VillageVotes,
        footsteps: VillageFootsteps
    ) : this(
        village = VillageView(village),
        day = day,
        dayChangeDatetime = if (village.status.isFinishedVillage()) null
        else village.days.latestDay().dayChangeDatetime,
        participateSituation = SituationAsParticipantView(situation, village),
        dispSpoilerSwitch = village.status.isSolved(),
        randomKeywords = randomKeywordList.joinToString(","),
        actionSituationList = VillageDaySituationsModel(
            village,
            day,
            abilities,
            situation.viewableSpoilerContent
        ),
        votes = VillageVotesModel(village, votes),
        footsteps = VillageDayFootstepsModel(
            village,
            day,
            footsteps,
            situation.viewableSpoilerContent
        )
    )
}

data class VillageDaySituationsModel(
    // 2dから現在の日付まで表示する
    val list: List<VillageDaySituationModel>
) {
    constructor(
        village: Village,
        day: Int,
        abilities: VillageAbilities,
        viewableSpoilerContent: Boolean
    ) : this(
        list = village.days.list
            .filter { it.day in 2..day }
            .map {
                VillageDaySituationModel(
                    village,
                    it.day,
                    abilities.filterByDay(it.day - 1),
                    viewableSpoilerContent
                )
            }
    )
}

data class VillageDaySituationModel(
    val day: Int,
    val attackedChara: String,
    val executedChara: String,
    val suddenlyDeathChara: String,
    val divinedChara: String?,
    val guardedChara: String?,
    val attack: String?,
    val investigation: String?
) {
    constructor(
        village: Village,
        day: Int,
        abilities: VillageAbilities,
        viewableSpoilerContent: Boolean
    ) : this(
        day = day,
        attackedChara = village.participant.list
            .filter { it.dead?.villageDay?.day == day && it.dead.isMiserable() }
            .map { it.shortName() }
            .shuffledAndJoin(),
        executedChara = village.participant.list
            .filter { it.dead?.villageDay?.day == day && it.dead.isExecuted() }
            .map { it.shortName() }
            .shuffledAndJoin(),
        suddenlyDeathChara = village.participant.list
            .filter { it.dead?.villageDay?.day == day && it.dead.isSuddenly() }
            .map { it.shortName() }
            .shuffledAndJoin(),
        divinedChara = if (viewableSpoilerContent) abilities.filterByAbility(AbilityType(CDef.AbilityType.占い)).list
            .joinToString("\n") {
                val myself = village.participant.member(it.myselfId).shortName()
                val target = village.participant.member(it.targetId!!).shortName()
                "$myself → $target"
            } else null,
        guardedChara = if (viewableSpoilerContent) abilities.filterByAbility(AbilityType(CDef.AbilityType.護衛)).list
            .joinToString("\n") {
                val myself = village.participant.member(it.myselfId).shortName()
                val target = village.participant.member(it.targetId!!).shortName()
                "$myself → $target"
            } else null,
        attack = if (viewableSpoilerContent) abilities.filterByAbility(AbilityType(CDef.AbilityType.護衛)).list
            .joinToString("\n") {
                val myself = village.participant.member(it.myselfId).shortName()
                val target = village.participant.member(it.targetId!!).shortName()
                "$myself → $target"
            } else null,
        investigation = if (viewableSpoilerContent) abilities.filterByAbility(AbilityType(CDef.AbilityType.捜査)).list
            .joinToString("\n") {
                val myself = village.participant.member(it.myselfId).shortName()
                "$myself → $${it.targetFootstep}"
            } else null
    )

    companion object {
        private fun List<String>.shuffledAndJoin(): String {
            return if (this.isEmpty()) "なし"
            else this.shuffled().joinToString("、")
        }
    }
}

data class VillageVotesModel(
    var maxVoteCount: Int,
    var memberVoteList: List<VillageMemberVoteModel>
) {
    constructor(
        village: Village,
        votes: VillageVotes
    ) : this(0, listOf()) {
        memberVoteList = village.participant.list
            .map {
                VillageMemberVoteModel(
                    village = village,
                    myself = it,
                    votes = votes.filterByMyself(it.id)
                )
            }
            .sortedByDescending { it.targetList.size }
        maxVoteCount = memberVoteList.map { it.targetList.size }.max() ?: 0
    }
}

data class VillageMemberVoteModel(
    val myself: VillageParticipantView,
    val targetList: List<VillageParticipantView>
) {
    constructor(
        village: Village,
        myself: VillageParticipant,
        votes: VillageVotes
    ) : this(
        myself = VillageParticipantView(village, myself),
        targetList = votes.list.sortedBy { it.day }.map {
            VillageParticipantView(
                village = village,
                participant = village.participant.member(it.targetId)
            )
        }
    )
}

data class VillageDayFootstepsModel(
    // 2dから現在の日付までを表示する
    val list: List<VillageDayFootstepModel>
) {
    constructor(
        village: Village,
        day: Int,
        footsteps: VillageFootsteps,
        viewableSpoilerContent: Boolean
    ) : this(
        list = village.days.list
            .filter { it.day in 2..day }
            .map {
                VillageDayFootstepModel(
                    day,
                    footsteps,
                    village.participant,
                    viewableSpoilerContent
                )
            }
    )
}

data class VillageDayFootstepModel(
    val day: Int,
    val footsteps: String
) {
    constructor(
        day: Int,
        footsteps: VillageFootsteps,
        participants: VillageParticipants,
        viewableSpoilerContent: Boolean
    ) : this(
        day = day,
        footsteps = if (viewableSpoilerContent)
            footsteps.convertToDayDispFootstepsWithSkill(participants, day - 1)
        else
            footsteps.convertToDayDispFootsteps(participants, day - 1)
    )
}
