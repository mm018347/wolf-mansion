package com.ort.wolfmansion.api.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController() {

    @GetMapping("/")
    fun index(): String {
        return "index"
    }
}