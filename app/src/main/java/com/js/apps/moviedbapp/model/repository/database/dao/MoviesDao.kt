package com.js.apps.moviedbapp.model.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.js.apps.moviedbapp.model.entities.media.Movie

@Dao
interface MoviesDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insert( items:Movie)

    @Query("SELECT * FROM Movie")
    fun getAll():LiveData<List<Movie>>

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getById(id:Int):LiveData<Movie>

    @Query("DELETE FROM Movie")
    fun deleteAll()
}