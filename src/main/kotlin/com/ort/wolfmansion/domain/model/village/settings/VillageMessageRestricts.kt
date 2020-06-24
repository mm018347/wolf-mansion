package com.ort.wolfmansion.domain.model.village.settings

import com.ort.dbflute.allcommon.CDef

data class VillageMessageRestricts(
    val list: List<VillageMessageRestrict>
) {

    fun restrict(cdefMessageType: CDef.MessageType): VillageMessageRestrict? {
        return list.find { it.messageType.toCdef() == cdefMessageType }
    }

    fun existsDifference(messageRestrict: VillageMessageRestricts): Boolean {
        if (list.size != messageRestrict.list.size) return true
        return list.any { restrict1 ->
            messageRestrict.list.none { restrict2 -> !restrict1.existsDifference(restrict2) }
        }
    }
}
