package com.emeraldscrolls.emeraldscrollsapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scrolls")
data class ScrollEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val text: String,
    val date: String,
)