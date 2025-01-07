package com.emeraldscrolls.emeraldscrollsapp

import androidx.room.Room
import com.emeraldscrolls.emeraldscrollsapp.data.dao.ScrollDao
import com.emeraldscrolls.emeraldscrollsapp.data.database.AppDatabase
import com.emeraldscrolls.emeraldscrollsapp.repository.ScrollRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            name = "database",
            context = androidContext(),
            klass = AppDatabase::class.java
        ).build()
    }
    single<ScrollDao> { get<AppDatabase>().scrollDao() }
    single { ScrollRepository(get()) }
    viewModel { MainViewModel(get()) }
}