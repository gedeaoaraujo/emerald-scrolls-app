package com.emeraldscrolls.emeraldscrollsapp.model

import java.time.LocalDateTime

data class NoteModel(
    var title: String = "",
    var text: String = "",
    var date: LocalDateTime? = null,
)
