package com.emeraldscrolls.emeraldscrollsapp.model

import java.time.LocalDateTime

data class ScrollModel(
    val id: Int = 0,
    var title: String = "",
    var text: String = "",
    var date: LocalDateTime? = null,
)
