package com.ort.wolfmansion.api.controller

import com.ort.wolfmansion.api.form.LoginForm
import com.ort.wolfmansion.api.view.model.index.IndexModel
import com.ort.wolfmansion.application.service.PlayerService
import com.ort.wolfmansion.application.service.VillageService
import com.ort.wolfmansion.domain.model.village.VillageStatus
import com.ort.wolfmansion.util.WolfMansionUserInfoUtil
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute

@Controller
class IndexController(
    private val villageService: VillageService,
    private val playerService: PlayerService
) {

    @GetMapping("/")
    fun index(
        @ModelAttribute("form") form: LoginForm,
        model: Model
    ): String {
        val villages = villageService.findVillages(villageStatusList = VillageStatus.listOfNotFinished())
        val isAvailableCreateVillage = WolfMansionUserInfoUtil.getUserInfo()?.let { user ->
            playerService.findPlayer(user).isAvailableCreateVillage(user)
        } ?: false
        val indexModel = IndexModel(villages, isAvailableCreateVillage)
        model.addAttribute("content", indexModel)
        return "index"
    }

    @GetMapping("/about")
    fun about(): String = "about"

    @GetMapping("/announce")
    fun announce(): String = "announce"

    @GetMapping("/rule")
    fun rule(): String = "rule"

    @GetMapping("/intro")
    fun intro(): String = "intro"

    @GetMapping("/practice")
    fun practice(): String = "practice"

    @GetMapping("/faq")
    fun faq(): String = "faq"
}