package com.js.apps.moviedbapp.model.repository.implementation

import android.util.Log
import androidx.lifecycle.LiveData
import com.js.apps.moviedbapp.model.core.ConnectivityHelper
import com.js.apps.moviedbapp.model.core.MediaTypes
import com.js.apps.moviedbapp.model.entities.media.Movie
import com.js.apps.moviedbapp.model.entities.media.Serie
import com.js.apps.moviedbapp.model.entities.media.Video
import com.js.apps.moviedbapp.model.repository.api.services.MediaItemsService
import com.js.apps.moviedbapp.model.repository.database.dao.MoviesDao
import com.js.apps.moviedbapp.model.repository.database.dao.SeriesDao
import javax.inject.Inject

class ItemRepository@Inject constructor(
    private val service :MediaItemsService,
    private val moviesDao : MoviesDao,
    private val seriesDao:SeriesDao,
    private val connectivityHelper: ConnectivityHelper
){
    suspend fun getItemVideos(id:Int, type:MediaTypes):List<Video>{
        Log.i("here", "getVideos")
        return service.getItemVideos(id, type)
    }
    fun getMovieItemFromDB( id:Int ):LiveData<Movie>{
        return moviesDao.getById(id)
    }
    fun getSerieItemFromDB( id:Int ):LiveData<Serie>{
        return seriesDao.getById(id)
    }
}