package com.js.apps.moviedbapp.model.repository.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.js.apps.moviedbapp.model.entities.media.Movie
import com.js.apps.moviedbapp.model.entities.media.Serie
import com.js.apps.moviedbapp.model.repository.database.dao.MoviesDao
import com.js.apps.moviedbapp.model.repository.database.dao.SeriesDao

@Database( entities = [Movie::class,Serie::class], version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun moviesDao():MoviesDao
    abstract fun seriesDao():SeriesDao
}