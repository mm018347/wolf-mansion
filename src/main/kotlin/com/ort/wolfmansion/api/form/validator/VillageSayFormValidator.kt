package com.ort.wolfmansion.api.form.validator

import com.ort.wolfmansion.api.form.VillageSayForm
import com.ort.wolfmansion.domain.model.message.MessageContent
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator


@Component
class VillageSayFormValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return VillageSayForm::class.java.isAssignableFrom(clazz)
    }

    override fun validate(target: Any, errors: Errors) {
        if (errors.hasErrors()) {
            // 既にエラーがある場合はチェックしない
            return
        }

        val form = target as VillageSayForm

        // 絵文字はこの時点で削除する
        form.message = removeSurrogate(form.message!!)
        val message = form.message ?: return
        // 末尾に改行文字列が含まれているとsplit時に削られるので削除してチェック
        val trimedMessage = message.trim { it <= ' ' }
        // 改行数＋それ以外の文字が400文字以上
        val length = trimedMessage.length
        val lineSeparatorNum = trimedMessage.split("\r\n".toRegex()).size - 1
        val messageLength = length - lineSeparatorNum
        if (messageLength <= 0 || MessageContent.lengthMax < messageLength) {
            errors.rejectValue("message", "VillageSayForm.validator.message.length")
        }
        // 行数が21以上
        if (trimedMessage.split("\r\n".toRegex()).size > MessageContent.lineMax) {
            errors.rejectValue("message", "VillageSayForm.validator.message.line")
        }
    }

    // 絵文字を削除
    private fun removeSurrogate(str: String): String {
        val sb = StringBuilder()
        for (c in str) {
            if (!c.toString().matches("[\\uD800-\\uDFFF]".toRegex())) {
                sb.append(c)
            }
        }
        return sb.toString()
    }
}