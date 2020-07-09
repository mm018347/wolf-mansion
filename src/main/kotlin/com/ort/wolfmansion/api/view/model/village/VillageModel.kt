package com.ort.wolfmansion.api.view.model.village

import com.ort.wolfmansion.api.view.domain.model.myself.SituationAsParticipantView
import com.ort.wolfmansion.api.view.domain.model.village.VillageView
import com.ort.wolfmansion.api.view.domain.model.village.participant.VillageParticipantView
import java.time.LocalDateTime

data class VillageModel(
    /** 村 */
    val village: VillageView,
    /** 何日めか */
    val day: Int,
    /** 次回更新日時 */
    val dayChangeDatetime: LocalDateTime,
    /** 参加情報 */
    val participateSituation: SituationAsParticipantView,
    /** ネタバレ情報を表示するか */
    val dispSpoilerContent: Boolean,
    /** ランダムキーワード(カンマ区切り) */
    val randomKeywords: String,

    // 状況欄
    /** 全体状況 */
    val actionSituationList: List<VillageDaySituationModel>,
    /** 投票 */
    val votes: VillageVotesModel,
    /** 足音 */
    val footsteps: List<VillageDayFootstepsModel>
) {
//    constructor(
//        village: Village,
//        day: Int
//    ) : this(
//
//    )
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
)

data class VillageVotesModel(
    val maxVoteCount: Int,
    val memberVoteList: List<VillageMemberVoteModel>
)

data class VillageMemberVoteModel(
    val myself: VillageParticipantView,
    val targetList: List<VillageParticipantView>
)

data class VillageDayFootstepsModel(
    val day: Int,
    val footsteps: String
)
