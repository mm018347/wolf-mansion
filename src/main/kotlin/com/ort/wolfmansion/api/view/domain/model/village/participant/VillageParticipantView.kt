package com.ort.wolfmansion.api.view.domain.model.village.participant

import com.ort.wolfmansion.api.view.domain.model.dead.DeadView
import com.ort.wolfmansion.domain.model.charachip.Chara
import com.ort.wolfmansion.domain.model.player.Player
import com.ort.wolfmansion.domain.model.skill.Skill
import com.ort.wolfmansion.domain.model.skill.SkillRequest
import com.ort.wolfmansion.domain.model.village.Village
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant
import com.ort.wolfmansion.domain.model.village.room.VillageRoom
import java.time.LocalDateTime

data class VillageParticipantView(
    val id: Int,
    val name: String,
    val shortName: String,
    val chara: Chara,
    val player: Player?,
    val room: VillageRoom?,
    val dead: DeadView?,
    val isSpectator: Boolean,
    val isGone: Boolean = false,
    val skill: Skill?,
    val skillRequest: SkillRequest?,
    val isWin: Boolean?,
    val lastAccessDatetime: LocalDateTime
) {
    constructor(
        village: Village,
        participant: VillageParticipant
    ) : this(
        id = participant.id,
        name = participant.name(),
        shortName = participant.shortName(),
        chara = participant.chara,
        player = if (village.status.isSolved()) participant.player else null,
        room = participant.room,
        dead = participant.dead?.let { DeadView(it, !village.status.isSolved()) },
        isSpectator = participant.isSpectator,
        skill = if (!village.status.isSolved()) null else participant.skill,
        skillRequest = if (!village.status.isSolved()) null else participant.skillRequest,
        isWin = participant.isWin,
        lastAccessDatetime = participant.lastAccessDatetime
    )
}