package com.js.apps.moviedbapp.model.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.js.apps.moviedbapp.model.entities.media.Serie
@Dao
interface SeriesDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insert( items: Serie)

    @Query("SELECT * FROM Serie")
    fun getAll(): LiveData<List<Serie>>

    @Query("SELECT * FROM Serie")
    suspend fun getAllSusp(): List<Serie>

    @Query("SELECT * FROM Serie WHERE id = :id")
    fun getById(id:Int): LiveData<Serie>

    @Query("DELETE FROM Serie")
    fun deleteAll()
}