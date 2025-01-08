package com.emeraldscrolls.emeraldscrollsapp.data.mappers

import com.emeraldscrolls.emeraldscrollsapp.data.entities.ScrollEntity
import com.emeraldscrolls.emeraldscrollsapp.model.ScrollModel
import java.time.LocalDateTime

fun ScrollEntity.toModel(): ScrollModel {
    return ScrollModel(
        id = id,
        title = title,
        text = text,
        date = LocalDateTime.parse(date)
    )
}