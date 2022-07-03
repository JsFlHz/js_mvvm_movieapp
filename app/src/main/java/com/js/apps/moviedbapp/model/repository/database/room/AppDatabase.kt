package com.js.apps.moviedbapp.model.repository.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.js.apps.moviedbapp.model.entities.media.MediaItem
import com.js.apps.moviedbapp.model.repository.database.dao.MediaItemDao
import javax.inject.Singleton

@Database( entities = [MediaItem::class], version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun mediaItemDao():MediaItemDao
}