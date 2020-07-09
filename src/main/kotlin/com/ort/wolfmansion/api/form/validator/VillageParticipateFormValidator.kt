package com.ort.wolfmansion.api.form.validator

import com.ort.wolfmansion.api.form.VillageParticipateForm
import com.ort.wolfmansion.domain.model.message.MessageContent
import org.springframework.validation.Errors
import org.springframework.validation.Validator


class VillageParticipateFormValidator : Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return VillageParticipateForm::class.java.isAssignableFrom(clazz)
    }

    override fun validate(target: Any, errors: Errors) {
        if (errors.hasErrors()) {
            // 既にエラーがある場合はチェックしない
            return
        }

        val form: VillageParticipateForm = target as VillageParticipateForm

        val message = form.joinMessage ?: return
        // 末尾に改行文字列が含まれているとsplit時に削られるので削除してチェック
        val trimedMessage = message.trim()
        // 改行数＋それ以外の文字が400文字以上
        val length = trimedMessage.length
        val lineSeparatorNum = trimedMessage.split("\r\n".toRegex()).size - 1
        val messageLength = length - lineSeparatorNum
        if (messageLength <= 0 || MessageContent.lengthMax < messageLength) {
            errors.rejectValue("joinMessage", "VillageSayForm.validator.message.length")
        }
        // 行数が21以上
        if (trimedMessage.split("\r\n".toRegex()).size > MessageContent.lineMax) {
            errors.rejectValue("joinMessage", "VillageSayForm.validator.message.line")
        }
    }
}