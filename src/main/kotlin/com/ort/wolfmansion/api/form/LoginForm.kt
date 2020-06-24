package com.ort.wolfmansion.api.form

import org.hibernate.validator.constraints.Length
import org.jetbrains.annotations.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class LoginForm(
    /** ユーザID */
    @field:NotNull
    @Size(min = 3, max = 12)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    val userId: String? = null,

    /** パスワード */
    @NotNull
    @Length(min = 3, max = 12)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    val password: String? = null,

    /** エラー有無 */
    val error: Boolean?
)