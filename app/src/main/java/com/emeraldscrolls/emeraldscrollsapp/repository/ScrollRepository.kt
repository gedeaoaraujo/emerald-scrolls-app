package com.emeraldscrolls.emeraldscrollsapp.repository

import com.emeraldscrolls.emeraldscrollsapp.data.dao.ScrollDao
import com.emeraldscrolls.emeraldscrollsapp.data.entities.ScrollEntity
import com.emeraldscrolls.emeraldscrollsapp.data.mappers.toModel
import com.emeraldscrolls.emeraldscrollsapp.model.ScrollModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ScrollRepository(
    private val scrollDao: ScrollDao
) {

    fun getAll(): Flow<List<ScrollModel>> {
        return scrollDao.selectAll().map {
            it.map { it.toModel() }
        }
    }

    suspend fun insert(scroll: ScrollModel){
        scrollDao.upsert(ScrollEntity(
            title = scroll.title,
            text = scroll.text,
            date = scroll.date.toString()
        ))
    }

}