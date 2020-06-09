package com.ort.wolfmansion.util

import java.time.LocalDate
import java.time.LocalDateTime

class WolfMansionDateUtil private constructor() {
    companion object {
        fun currentLocalDateTime(): LocalDateTime {
            return LocalDateTime.now()
        }

        fun currentLocalDate(): LocalDate {
            return LocalDate.now()
        }
    }
}