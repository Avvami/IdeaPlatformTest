package com.personal.ideaplatformtest.core.domain.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun formatDateTime(date: LocalDateTime): String {
    return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
}