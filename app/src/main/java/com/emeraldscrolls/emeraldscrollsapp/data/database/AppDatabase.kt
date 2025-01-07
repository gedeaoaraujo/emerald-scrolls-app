package com.emeraldscrolls.emeraldscrollsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emeraldscrolls.emeraldscrollsapp.data.dao.ScrollDao
import com.emeraldscrolls.emeraldscrollsapp.data.entities.ScrollEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [ScrollEntity::class]
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun scrollDao(): ScrollDao
}