package com.ort.wolfmansion.fw.interceptor

import com.ort.wolfmansion.fw.security.WolfMansionUser
import org.springframework.lang.Nullable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class SetUserInfoInterceptor : HandlerInterceptorAdapter() {

    @Throws(Exception::class)
    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any, @Nullable modelAndView: ModelAndView?
    ) {
        val authentication = SecurityContextHolder.getContext().authentication ?: return
        if (authentication.principal is WolfMansionUser) {
            val user = WolfMansionUser::class.java.cast(authentication.principal)
            modelAndView?.addObject("user", user)
        }
    }
}