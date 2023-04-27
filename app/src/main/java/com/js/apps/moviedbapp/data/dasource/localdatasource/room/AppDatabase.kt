package com.js.apps.moviedbapp.data.dasource.localdatasource.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.js.apps.moviedbapp.data.dasource.localdatasource.room.dao.MoviesDao
import com.js.apps.moviedbapp.data.dasource.localdatasource.room.dao.SeriesDao

import com.js.apps.moviedbapp.data.model.Movie
import com.js.apps.moviedbapp.data.model.Serie


@Database( entities = [Movie::class, Serie::class], version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun moviesDao():MoviesDao
    abstract fun seriesDao():SeriesDao
}