package com.emeraldscrolls.emeraldscrollsapp.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.dayAndMonth(): String {
    val formatter = DateTimeFormatter
        .ofPattern("dd MMMM")
    return this.format(formatter)
}

fun LocalDateTime.yearAndWeekday(): String {
    val formatter = DateTimeFormatter
        .ofPattern("yyyy, EEEE")
    return this.format(formatter)
}

fun LocalDateTime.hourAndMinute(): String {
    val formatter = DateTimeFormatter
        .ofPattern("HH:mm")
    return this.format(formatter)
}

fun LocalDateTime.dateTimeFmt(): String {
    val formatter = DateTimeFormatter
        .ofPattern("dd/MM/yy HH:mm")
    return this.format(formatter)
}

fun LocalDateTime.dateTimeFile(): String {
    val formatter = DateTimeFormatter
        .ofPattern("ddMMyyyy-HHmmss")
    return this.format(formatter)
}