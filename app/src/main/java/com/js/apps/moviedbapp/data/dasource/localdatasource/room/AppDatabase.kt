package com.js.apps.moviedbapp.data.dasource.localdatasource.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.js.apps.moviedbapp.data.dasource.localdatasource.room.dao.MoviesDao
import com.js.apps.moviedbapp.data.dasource.localdatasource.room.dao.SeriesDao

import com.js.apps.moviedbapp.domain.media.MovieModel
import com.js.apps.moviedbapp.domain.media.SerieModel


@Database( entities = [MovieModel::class, SerieModel::class], version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun moviesDao():MoviesDao
    abstract fun seriesDao():SeriesDao
}