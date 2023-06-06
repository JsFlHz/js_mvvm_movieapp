package com.js.apps.moviedbapp.data.dasource.localdatasource.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.js.apps.moviedbapp.domain.media.MovieModel

@Dao
interface MoviesDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insert( items: List<MovieModel>)

    @Query("SELECT * FROM Movie order by voteAverage desc")
    fun getAll():LiveData<List<MovieModel>>

    @Query("SELECT * FROM Movie order by voteAverage desc")
    suspend fun getAllSusp():List<MovieModel>

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getById(id:Int):LiveData<MovieModel>

    @Query("DELETE FROM Movie")
    fun deleteAll()
}