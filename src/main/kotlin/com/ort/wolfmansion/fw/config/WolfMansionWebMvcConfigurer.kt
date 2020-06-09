package com.ort.wolfmansion.fw.config

import com.ort.wolfmansion.fw.interceptor.AccessContextInterceptor
import com.ort.wolfmansion.fw.interceptor.SetUserInfoInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

class WolfMansionWebMvcConfigurer : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(AccessContextInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**")
        registry.addInterceptor(SetUserInfoInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**")
    }
}