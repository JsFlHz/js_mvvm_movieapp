package com.js.apps.moviedbapp.data.repository

import android.provider.MediaStore.Video
import androidx.lifecycle.LiveData
import com.js.apps.moviedbapp.data.core.MediaTypes
import com.js.apps.moviedbapp.data.model.Movie
import com.js.apps.moviedbapp.data.model.Serie


interface ItemRepository {
    suspend fun getItemVideos(id:Int, type:MediaTypes):List<Video>
    fun getMovieItemFromDB( id:Int ): LiveData<Movie>
    fun getSerieItemFromDB( id:Int ): LiveData<Serie>
}
