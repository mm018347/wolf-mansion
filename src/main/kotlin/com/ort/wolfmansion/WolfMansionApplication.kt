package com.ort.wolfmansion

import com.ort.dbflute.allcommon.DBFluteBeansJavaConfig
import com.ort.wolfmansion.fw.config.WolfMansionWebMvcConfigurer
import com.ort.wolfmansion.fw.config.WolfMansionWebSecurityConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(
    DBFluteBeansJavaConfig::class,
    WolfMansionWebMvcConfigurer::class,
    WolfMansionWebSecurityConfig::class
)
class WolfMansionApplication

fun main(args: Array<String>) {
    runApplication<WolfMansionApplication>(*args)
}
