package com.emeraldscrolls.emeraldscrollsapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.emeraldscrolls.emeraldscrollsapp.data.entities.ScrollEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScrollDao {

    @Upsert
    suspend fun upsert(scroll: ScrollEntity)

    @Query("SELECT * FROM scrolls")
    fun selectAll(): Flow<List<ScrollEntity>>

    @Query("DELETE FROM scrolls WHERE id = :itemId")
    suspend fun deleteById(itemId: Int)

}