package com.ort.wolfmansion.api.view.domain.model.myself

import com.ort.wolfmansion.api.view.domain.model.village.participant.VillageParticipantView
import com.ort.wolfmansion.domain.model.message.MessageType
import com.ort.wolfmansion.domain.model.myself.SayMessageTypeSituation
import com.ort.wolfmansion.domain.model.myself.SayRestrictSituation
import com.ort.wolfmansion.domain.model.village.Village

data class SayMessageTypeSituationView(
    val messageType: MessageType,
    val restrict: SayRestrictSituation,
    // 秘話用
    val targetList: List<VillageParticipantView>
) {
    constructor(
        situation: SayMessageTypeSituation,
        village: Village
    ) : this(
        messageType = situation.messageType,
        restrict = situation.restrict,
        targetList = situation.targetList.map { VillageParticipantView(village, it) }
    )
}
