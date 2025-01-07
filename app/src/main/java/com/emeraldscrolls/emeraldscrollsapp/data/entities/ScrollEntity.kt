package com.emeraldscrolls.emeraldscrollsapp.data.entities

import androidx.room.Entity

@Entity(tableName = "scrolls")
data class ScrollEntity(
    val title: String,
    val text: String,
    val date: String,
)