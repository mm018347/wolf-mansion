package com.ort.wolfmansion.api.view.response

import com.ort.wolfmansion.domain.model.charachip.Charachips
import com.ort.wolfmansion.domain.model.village.Village
import java.time.format.DateTimeFormatter

data class RecruitingVillagesResponse(
    val villageList: List<RecruitingVillageResponse>
) {
    constructor(
        villageList: List<Village>,
        charachips: Charachips
    ) : this(
        villageList = villageList.map { RecruitingVillageResponse(it, charachips) }
    )
}

data class RecruitingVillageResponse(
    val id: Int,
    val name: String,
    val status: String,
    val participantCount: Int,
    val participantCapacity: Int,
    val spectateCount: Int,
    val dayChangeTime: String,
    val dayChangeInterval: String,
    val startDatetime: String,
    val charachipName: String,
    val restrict: String,
    val url: String
) {
    constructor(
        village: Village,
        charachips: Charachips
    ) : this(
        id = village.id,
        name = village.name,
        status = village.status.name,
        participantCount = village.participant.count,
        participantCapacity = village.setting.capacity.max,
        spectateCount = village.spectator.count,
        dayChangeTime = village.days.latestDay().dayChangeDatetime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")),
        dayChangeInterval = village.setting.time.dayChangeIntervalSeconds.let {
            if (it >= 3600) "${it / 3600}h"
            else "${it / 60}m"
        },
        startDatetime = village.setting.time.startDatetime.format(DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm")),
        charachipName = charachips.list.first { it.id == village.setting.charachip.charachipId }.name,
        restrict = if (village.setting.rules.messageRestrict.list.isEmpty()) "なし" else "あり",
        url = "https://wolfort.net/wolf-mansion/village/${village.id}"
    )
}
