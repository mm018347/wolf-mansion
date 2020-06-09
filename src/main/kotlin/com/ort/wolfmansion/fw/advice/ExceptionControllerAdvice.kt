package com.ort.wolfmansion.fw.advice

import org.slf4j.LoggerFactory
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.ModelAttribute

@ControllerAdvice
class ExceptionControllerAdvice {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private val logger = LoggerFactory.getLogger(ExceptionControllerAdvice::class.java)

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    @InitBinder
    fun initBinder(binder: WebDataBinder) {
    }

    // ===================================================================================
    //                                                                             Execute
    //                                                                             =======
    @ExceptionHandler(Exception::class)
    fun exception(e: Exception): String {
        logger.error(e.message, e)
        return "error"
    }

    @ModelAttribute
    fun modelAttribute() {
    }
}