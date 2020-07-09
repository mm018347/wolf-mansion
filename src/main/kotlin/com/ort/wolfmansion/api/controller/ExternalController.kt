package com.ort.wolfmansion.api.controller

import com.ort.wolfmansion.api.view.response.RecruitingVillagesResponse
import com.ort.wolfmansion.application.service.CharachipService
import com.ort.wolfmansion.application.service.VillageService
import com.ort.wolfmansion.domain.model.village.VillageStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ExternalController(
    private val villageService: VillageService,
    private val charachipService: CharachipService
) {

    @GetMapping("/recruiting-village-list")
    @ResponseBody
    fun recruitingVillageList(): RecruitingVillagesResponse {
        val villageList = villageService.findVillages(
            villageStatusList = VillageStatus.listOfNotFinished()
        ).list.sortedBy { it.id }

        val charachips = charachipService.findCharaChips()

        return RecruitingVillagesResponse(
            villageList = villageList,
            charachips = charachips
        )
    }

}