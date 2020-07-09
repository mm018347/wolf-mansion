package com.ort.wolfmansion.api.view.domain.model.myself

import com.ort.wolfmansion.domain.model.charachip.CharaFace
import com.ort.wolfmansion.domain.model.message.MessageType
import com.ort.wolfmansion.domain.model.myself.SaySituation
import com.ort.wolfmansion.domain.model.village.Village

data class SaySituationView(
    val isAvailableSay: Boolean,
    val selectableMessageTypeList: List<SayMessageTypeSituationView>,
    val selectableFaceTypeList: List<CharaFace>,
    val defaultMessageType: MessageType?
) {
    constructor(
        situation: SaySituation,
        village: Village
    ) : this(
        isAvailableSay = situation.isAvailableSay,
        selectableMessageTypeList = situation.selectableMessageTypeList.map {
            SayMessageTypeSituationView(it, village)
        },
        selectableFaceTypeList = situation.selectableFaceTypeList,
        defaultMessageType = situation.defaultMessageType
    )
}
