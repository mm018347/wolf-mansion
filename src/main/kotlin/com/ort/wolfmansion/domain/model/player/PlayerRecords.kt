package com.ort.wolfmansion.domain.model.player

import com.ort.wolfmansion.domain.model.player.record.CampRecord
import com.ort.wolfmansion.domain.model.player.record.ParticipateVillage
import com.ort.wolfmansion.domain.model.player.record.Record
import com.ort.wolfmansion.domain.model.player.record.SkillRecord

data class PlayerRecords(
    val player: Player,
    val wholeRecord: Record,
    val campRecordList: List<CampRecord>,
    val skillRecordList: List<SkillRecord>,
    val participateVillageList: List<ParticipateVillage>
) {
}