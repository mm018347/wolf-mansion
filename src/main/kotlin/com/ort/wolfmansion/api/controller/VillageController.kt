package com.ort.wolfmansion.api.controller

import com.ort.wolfmansion.api.form.VillageAbilityForm
import com.ort.wolfmansion.api.form.VillageChangeRequestSkillForm
import com.ort.wolfmansion.api.form.VillageCommitForm
import com.ort.wolfmansion.api.form.VillageGetAnchorMessageForm
import com.ort.wolfmansion.api.form.VillageGetFootstepListForm
import com.ort.wolfmansion.api.form.VillageGetMessageListForm
import com.ort.wolfmansion.api.form.VillageParticipateForm
import com.ort.wolfmansion.api.form.VillageSayForm
import com.ort.wolfmansion.api.form.VillageSettingsForm
import com.ort.wolfmansion.api.form.VillageVoteForm
import com.ort.wolfmansion.api.form.validator.VillageParticipateFormValidator
import com.ort.wolfmansion.api.form.validator.VillageSayFormValidator
import com.ort.wolfmansion.api.form.validator.VillageSettingsFormValidator
import com.ort.wolfmansion.api.view.domain.model.message.MessageView
import com.ort.wolfmansion.api.view.model.village.VillageModel
import com.ort.wolfmansion.api.view.model.village_list.VillageListModel
import com.ort.wolfmansion.api.view.response.VillageAnchorMessageResponse
import com.ort.wolfmansion.api.view.response.VillageGetFootstepListResponse
import com.ort.wolfmansion.api.view.response.VillageLatestMessageDatetimeResponse
import com.ort.wolfmansion.api.view.response.VillageMessageListResponse
import com.ort.wolfmansion.api.view.response.VillageSayConfirmResponse
import com.ort.wolfmansion.application.coordinator.MessageCoordinator
import com.ort.wolfmansion.application.coordinator.VillageCoordinator
import com.ort.wolfmansion.application.service.CommitService
import com.ort.wolfmansion.application.service.VillageService
import com.ort.wolfmansion.application.service.VoteService
import com.ort.wolfmansion.util.WolfMansionUserInfoUtil
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.time.format.DateTimeFormatter


@Controller
class VillageController(
    // form validator
    private val villageSayFormValidator: VillageSayFormValidator,
    private val villageParticipateFormValidator: VillageParticipateFormValidator,
    private val villageSettingsFormValidator: VillageSettingsFormValidator,
    // application service
    private val villageCoordinator: VillageCoordinator,
    private val villageService: VillageService,
    private val messageCoordinator: MessageCoordinator,
    private val commitService: CommitService,
    private val voteService: VoteService
) {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @InitBinder("sayForm")
    fun initBinder(binder: WebDataBinder) {
        binder.addValidators(villageSayFormValidator)
    }

    @InitBinder("participateForm")
    fun initBinderParticipate(binder: WebDataBinder) {
        binder.addValidators(villageParticipateFormValidator)
    }

    @InitBinder("settingsForm")
    fun initBinderChangeSettings(binder: WebDataBinder) {
        binder.addValidators(villageSettingsFormValidator)
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    // 村一覧初期表示
    @GetMapping("/village-list")
    private fun villageListIndex(
        model: Model
    ): String {
        val villages = villageService.findVillages()
        model.addAttribute("content", VillageListModel(villages))
        return "village-list"
    }

    // 村最新日付初期表示
    @GetMapping("/village/{villageId}")
    private fun villageIndex(
        @PathVariable villageId: Int,
        model: Model
    ): String {
        val village = villageService.findVillage(villageId)
        val content = VillageModel(
            village = village,
            day = village.days.latestDay().day
        )
        model.addAttribute("content", content)
        return "village"
    }

    // 村日付指定初期表示
    @GetMapping("/village/{villageId}/day/{day}")
    private fun villageDayIndex(
        @PathVariable villageId: Int,
        @PathVariable day: Int,
        model: Model
    ): String {
        // TODO
        return "village"
    }

    // 発言取得
    @GetMapping("/village/{villageId}/getMessageList")
    @ResponseBody
    private fun getDayMessageList(
        @PathVariable villageId: Int,
        @Validated form: VillageGetMessageListForm
    ): VillageMessageListResponse {
        val village = villageService.findVillage(villageId, false)
        val user = WolfMansionUserInfoUtil.getUserInfo()
        val participant = villageCoordinator.findParticipant(village, user)
        val messages = messageCoordinator.findMessages(
            village = village,
            day = form.day ?: village.days.latestDay().day,
            user = user,
            pageSize = form.pageSize,
            pageNum = form.pageNum
        )
        val commits = commitService.findCommits(villageId)
        val votes = voteService.findVillageVotes(villageId)
        val isLatestDay = form.day == null || form.day == village.days.latestDay().day
        return VillageMessageListResponse(
            village = village,
            messages = messages,
            commits = commits,
            votes = votes,
            isLatestDay = isLatestDay,
            isParticipating = participant != null
        )
    }

    // 最終発言時間取得
    @GetMapping("/village/{villageId}/getLatestMessageDatetime")
    @ResponseBody
    private fun getLatestMessageDatetime(
        @PathVariable villageId: Int,
        form: VillageGetMessageListForm
    ): VillageLatestMessageDatetimeResponse {
        val village = villageService.findVillage(villageId)
        val user = WolfMansionUserInfoUtil.getUserInfo()
        val datetime = messageCoordinator.findLatestMessagesDatetime(village, user)
        return VillageLatestMessageDatetimeResponse(datetime?.format(DateTimeFormatter.ofPattern("uuuuMMddHHmmss")) ?: "0")
    }

    // アンカー発言取得
    @GetMapping("/village/{villageId}/getAnchorMessage")
    @ResponseBody
    private fun getAnchorMessage(
        @PathVariable villageId: Int,
        @Validated form: VillageGetAnchorMessageForm,
        result: BindingResult
    ): VillageAnchorMessageResponse? {
        if (result.hasErrors()) return null
        val village = villageService.findVillage(villageId)
        val user = WolfMansionUserInfoUtil.getUserInfo()
        val message = messageCoordinator.findMessage(village, form.messageType!!, form.messageNumber!!, user)
        return message?.let {
            VillageAnchorMessageResponse(message = MessageView(it, village))
        }
    }

    // 入村確認画面へ
    @PostMapping("/village/{villageId}/confirm-participate")
    private fun confirmParticipate(
        @PathVariable villageId: Int?,
        @Validated @ModelAttribute("participateForm") participateForm: VillageParticipateForm,
        result: BindingResult,
        model: Model
    ): String {
        return "participate-confirm"
    }

    // 入村
    @PostMapping("/village/{villageId}/participate")
    private fun participate(
        @PathVariable villageId: Int?,
        @Validated @ModelAttribute("participateForm") participateForm: VillageParticipateForm,
        result: BindingResult,
        model: Model
    ): String {
        return "redirect:/village/$villageId#bottom"
    }

    // 希望役職変更
    @PostMapping("/village/{villageId}/change-skill")
    private fun changeSkill(
        @PathVariable villageId: Int?,
        @Validated @ModelAttribute("changeRequestSkill") changeRequestSkillForm: VillageChangeRequestSkillForm,
        result: BindingResult,
        model: Model
    ): String {
        return "redirect:/village/$villageId#bottom"
    }

    // 退村
    @PostMapping("/village/{villageId}/leave")
    private fun leave(
        @PathVariable villageId: Int?,
        model: Model
    ): String {
        return "redirect:/village/$villageId#bottom"
    }

    // 発言確認画面へ
    @PostMapping("/village/{villageId}/confirm")
    @ResponseBody
    private fun confirm(
        @PathVariable villageId: Int?,
        @Validated @ModelAttribute("sayForm") sayForm: VillageSayForm,
        result: BindingResult,
        model: Model
    ): VillageSayConfirmResponse? {
        return null
    }

    // 発言する
    @PostMapping("/village/{villageId}/say")
    private fun say(
        @PathVariable villageId: Int?,
        @Validated @ModelAttribute("sayForm") sayForm: VillageSayForm,
        result: BindingResult,
        model: Model
    ): String {
        return "redirect:/village/$villageId#bottom"
    }

    // 能力セットする
    @PostMapping("/village/{villageId}/setAbility")
    private fun setAbility(
        @PathVariable villageId: Int?,
        @Validated @ModelAttribute("abilityForm") abilityForm: VillageAbilityForm,
        result: BindingResult,
        model: Model
    ): String {
        return "redirect:/village/$villageId#bottom"
    }

    // 投票セットする
    @PostMapping("/village/{villageId}/setVote")
    private fun setVote(
        @PathVariable villageId: Int,
        @Validated @ModelAttribute("voteForm") voteForm: VillageVoteForm,
        result: BindingResult,
        model: Model
    ): String {
        return "redirect:/village/$villageId#bottom"
    }

    // コミットする
    @PostMapping("/village/{villageId}/commit")
    private fun setCommit(
        @PathVariable villageId: Int,
        @Validated @ModelAttribute("commitForm") commitForm: VillageCommitForm,
        result: BindingResult,
        model: Model
    ): String {
        return "redirect:/village/$villageId#bottom"
    }

    // 足音候補取得
    @GetMapping("/village/{villageId}/getFootstepList")
    @ResponseBody
    private fun getFootstepList(
        @PathVariable villageId: Int,
        @Validated form: VillageGetFootstepListForm,
        result: BindingResult
    ): VillageGetFootstepListResponse {
        return VillageGetFootstepListResponse(listOf())
    }

    // 村設定変更
    @GetMapping("/village/{villageId}/settings")
    private fun settingsIndex(
        @PathVariable villageId: Int,
        model: Model
    ): String {
        return "village-settings"
    }

    // 村設定変更
    @PostMapping("/village/{villageId}/settings")
    private fun storeSettings(
        @PathVariable villageId: Int,
        @Validated @ModelAttribute("settingsForm") form: VillageSettingsForm,
        bindingResult: BindingResult,
        model: Model
    ): String {
        return "redirect:/village/$villageId#bottom"
    }
}