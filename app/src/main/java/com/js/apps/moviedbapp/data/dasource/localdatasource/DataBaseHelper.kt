package com.js.apps.moviedbapp.data.dasource.localdatasource

import android.app.Application
import androidx.room.Room
import com.js.apps.moviedbapp.data.dasource.localdatasource.room.AppDatabase
import javax.inject.Singleton

class DataBaseHelper(private val context: Application) {
    @Singleton
    fun applicationDatabase(): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "js_moviecatalog_db"
        ).build()
    }
}