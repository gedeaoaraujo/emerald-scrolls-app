package com.emeraldscrolls.emeraldscrollsapp.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun dateTimeFmt(): String {
    val formatter = DateTimeFormatter
        .ofPattern("dd/MM/yy - HH:mm:ss")
    return LocalDateTime.now().format(formatter)
}