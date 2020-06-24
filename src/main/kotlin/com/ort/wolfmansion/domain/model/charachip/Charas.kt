package com.ort.wolfmansion.domain.model.charachip

import com.ort.wolfmansion.domain.model.village.participant.VillageParticipants

data class Charas(
    val list: List<Chara>
) {
    fun chara(charaId: Int): Chara = list.firstOrNull { it.id == charaId } ?: throw IllegalStateException("not exist. charaId: $charaId")

    fun chara(
        participants: VillageParticipants,
        participantId: Int
    ): Chara = chara(participants.member(participantId).charaId)
}
