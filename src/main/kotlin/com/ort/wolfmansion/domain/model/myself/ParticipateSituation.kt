package com.ort.wolfmansion.domain.model.myself

import com.ort.wolfmansion.domain.model.charachip.Chara

data class ParticipateSituation(
    val isParticipating: Boolean,
    val isAvailableParticipate: Boolean,
    val isAvailableSpectate: Boolean,
    val selectableCharaList: List<Chara>,
    val isAvailableLeave: Boolean
) {

}
