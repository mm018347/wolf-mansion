package com.ort.wolfmansion.domain.model.myself

import com.ort.wolfmansion.domain.model.message.MessageType
import com.ort.wolfmansion.domain.model.village.participant.VillageParticipant

data class SayMessageTypeSituation(
    val messageType: MessageType,
    val restrict: SayRestrictSituation,
    // 秘話用
    val targetList: List<VillageParticipant>
) {

}
