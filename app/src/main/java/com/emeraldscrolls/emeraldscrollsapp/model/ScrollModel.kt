package com.emeraldscrolls.emeraldscrollsapp.model

import java.time.LocalDateTime

data class ScrollModel(
    var title: String = "",
    var text: String = "",
    var date: LocalDateTime? = null,
)
