package com.js.apps.moviedbapp.data.dasource.localdatasource.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.js.apps.moviedbapp.domain.media.SerieModel

@Dao
interface SeriesDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insert( items: List<SerieModel>)

    @Query("SELECT * FROM Serie order by voteAverage desc")
    fun getAll(): LiveData<List<SerieModel>>

    @Query("SELECT * FROM Serie order by voteAverage desc")
    suspend fun getAllSusp(): List<SerieModel>

    @Query("SELECT * FROM Serie WHERE id = :id")
    fun getById(id:Int): LiveData<SerieModel>

    @Query("DELETE FROM Serie")
    fun deleteAll()
}