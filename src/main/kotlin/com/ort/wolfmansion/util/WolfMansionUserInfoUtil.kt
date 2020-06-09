package com.ort.wolfmansion.util

import com.ort.wolfmansion.fw.security.WolfMansionUser
import org.springframework.security.core.context.SecurityContextHolder

class WolfMansionUserInfoUtil private constructor() {

    companion object {
        fun getUserInfo(): WolfMansionUser? {
            val authentication = SecurityContextHolder.getContext().authentication ?: return null
            return if (authentication.getPrincipal() is WolfMansionUser) {
                WolfMansionUser::class.java.cast(authentication.principal)
            } else null
        }
    }
}