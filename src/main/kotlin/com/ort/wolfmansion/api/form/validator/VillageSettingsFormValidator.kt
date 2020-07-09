package com.ort.wolfmansion.api.form.validator

import com.ort.dbflute.allcommon.CDef
import com.ort.wolfmansion.api.form.VillageSettingsForm
import com.ort.wolfmansion.domain.model.village.settings.VillageOrganizations
import com.ort.wolfmansion.domain.model.village.settings.VillagePassword
import com.ort.wolfmansion.domain.model.village.settings.VillageTime
import com.ort.wolfmansion.util.WolfMansionDateUtil
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import java.time.DateTimeException


class VillageSettingsFormValidator : Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return VillageSettingsForm::class.java!!.isAssignableFrom(clazz)
    }

    override fun validate(target: Any, errors: Errors) {
        if (errors.hasErrors()) {
            // 既にエラーがある場合はチェックしない
            return
        }

        val form = target as VillageSettingsForm

        // 定員＜最低開始人数
        if (form.personNumMax!! < form.startPersonMinNum!!) {
            errors.rejectValue("personMaxNum", "NewVillageForm.validator.personMaxNum")
        }

        // 更新間隔
        validateInterval(errors, form)

        // 開始日時
        validateStartDatetime(errors, form)

        // 入村パスワード
        validatePassword(errors, form)

        // 構成
        validateOrganization(errors, form)

        // 発言制限
        validateSayRestrict(errors, form)
    }

    // 更新間隔
    private fun validateInterval(
        errors: Errors,
        form: VillageSettingsForm
    ) {
        val intervalSeconds = form.toIntervalSeconds()
        if (intervalSeconds < 60 || VillageTime.dayChangeIntervalHours_max * 60 * 60 < intervalSeconds) {
            errors.rejectValue("dayChangeIntervalHours", "NewVillageForm.validator.dayChangeIntervalHours")
        }
    }

    // 開始日時
    private fun validateStartDatetime(
        errors: Errors,
        form: VillageSettingsForm
    ) {
        try {
            val startDateTime = form.toStratDatetime()
            if (startDateTime.isBefore(WolfMansionDateUtil.currentLocalDateTime())) {
                errors.rejectValue("startYear", "NewVillageForm.validator.startYear")
            }
        } catch (e: DateTimeException) {
            errors.rejectValue("startYear", "NewVillageForm.validator.startYear")
        }
    }

    // パスワード
    private fun validatePassword(
        errors: Errors,
        form: VillageSettingsForm
    ) {
        if (!form.joinPassword.isNullOrEmpty()) {
            form.joinPassword.let {
                if (it.length < VillagePassword.joinPassword_length_min
                    || VillagePassword.joinPassword_length_max < it.length
                ) {
                    errors.rejectValue("joinPassword", "VillageSayForm.validator.joinPassword.length")
                }
            }
        }
    }

    // 編成
    private fun validateOrganization(
        errors: Errors,
        form: VillageSettingsForm
    ) {
        val organization = form.organization
        // 未入力
        if (organization.isNullOrEmpty()) {
            errors.rejectValue("organization", "VillageSayForm.validator.organization.required")
            return
        }

        val organizationList = organization.replace("\r\n", "\n").split("\n".toRegex())

        // 最低人数〜最大人数までの構成が存在するか
        val minNum = form.startPersonMinNum!!
        val maxNum = form.personNumMax!!
        for (personNum in minNum..maxNum) {
            if (organizationList.none { orgStr -> orgStr.length == personNum }) {
                errors.rejectValue("organization", "NewVillageForm.validator.organization.lines")
                return
            }
            if (organizationList.count { orgStr -> orgStr.length == personNum } > 1) {
                errors.rejectValue("organization", "NewVillageForm.validator.organization.duplicate", arrayOf(personNum), null)
                return
            }
        }

        // 行ごとにチェック
        for (org in organizationList) {
            val personNum = org.length
            // 存在しない役職
            for (character in org.toCharArray()) {
                val existsSkill = CDef.Skill.listAll()
                    .filterNot { skill -> CDef.Skill.listOfSomeoneSkill().contains(skill) }
                    .map { it.shortName() }
                    .any { it == character.toString() }
                if (!existsSkill) {
                    errors.rejectValue(
                        "organization", "NewVillageForm.validator.organization.noexistskill",
                        arrayOf(personNum, character.toString()), null
                    )
                    return
                }
            }
            // 役職人数制限
            if (isInvalidOrganizationSkillPersonNum(errors, org, personNum)) {
                return
            }
        }
    }

    private fun isInvalidOrganizationSkillPersonNum(
        errors: Errors,
        org: String,
        personNum: Int
    ): Boolean {
        val skillPersonNumMap = VillageOrganizations(mapOf(personNum to org)).mapToSkillCount(personNum)
        // 村人がいない
        if (skillPersonNumMap.getOrDefault(CDef.Skill.村人, 0) < 1) {
            errors.rejectValue("organization", "NewVillageForm.validator.organization.noexistvillager", arrayOf<Any>(personNum), null)
            return true
        }
        // 人狼がいない
        val wolfsNum = CDef.Skill.listOfHasAttackAbility().sumBy { skill -> skillPersonNumMap.getOrDefault(skill, 0) }
        if (wolfsNum < 1) {
            errors.rejectValue("organization", "NewVillageForm.validator.organization.noexistwerewolf", arrayOf<Any>(personNum), null)
            return true
        }
        // 人狼の人数が過半数を超えている
        if (wolfsNum > org.length / 2) {
            errors.rejectValue("organization", "NewVillageForm.validator.organization.werewolfwin", arrayOf<Any>(personNum), null)
            return true
        }

        return false
    }

    // 発言制限
    private fun validateSayRestrict(errors: Errors, form: VillageSettingsForm) {
        val detailList = form.sayRestrictList!!.flatMap { it.detailList!! }
        val isCountInvalid =
            detailList.any { detail -> detail.restrict == true && (detail.count == null || detail.count < 0 || detail.count > 100) }
        val isLengthInvalid =
            detailList.any { detail -> detail.restrict == true && (detail.length == null || detail.length < 0 || detail.length > 400) }
        if (isCountInvalid || isLengthInvalid) {
            errors.rejectValue("sayRestrictList", "NewVillageForm.validator.sayRestrictList", arrayOf(), null)
        }
    }
}
