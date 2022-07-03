package com.js.apps.moviedbapp.model.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.js.apps.moviedbapp.model.entities.media.MediaItem

@Dao
interface MediaItemDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insert( items:MediaItem)

    @Query("SELECT * FROM MediaItem")
    fun getAll():LiveData<List<MediaItem>>

    @Query("SELECT * FROM MediaItem WHERE id = :id")
    fun getById(id:Int):LiveData<MediaItem>

    @Query("DELETE FROM MediaItem")
    fun deleteAll()
}