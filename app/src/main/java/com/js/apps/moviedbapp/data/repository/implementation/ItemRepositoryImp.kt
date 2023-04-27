package com.js.apps.moviedbapp.data.repository.implementation

import androidx.lifecycle.LiveData
import com.js.apps.moviedbapp.data.dasource.localdatasource.room.dao.MoviesDao
import com.js.apps.moviedbapp.data.dasource.localdatasource.room.dao.SeriesDao
import com.js.apps.moviedbapp.data.dasource.remotedatasource.services.MediaItemsService
import com.js.apps.moviedbapp.data.model.Movie
import com.js.apps.moviedbapp.data.model.Serie
import com.js.apps.moviedbapp.data.model.Video

import javax.inject.Inject

class ItemRepositoryImp@Inject constructor(
    private val service :MediaItemsService,
    private val moviesDao : MoviesDao,
    private val seriesDao:  SeriesDao,
    private val connectivityHelper: com.js.apps.moviedbapp.data.core.ConnectivityHelper
){
    suspend fun getItemVideos(id:Int, type: com.js.apps.moviedbapp.data.core.MediaTypes):List<Video>{
        return service.getItemVideos(id, type)
    }
    fun getMovieItemFromDB( id:Int ):LiveData<Movie>{
        return moviesDao.getById(id)
    }
    fun getSerieItemFromDB( id:Int ):LiveData<Serie>{
        return seriesDao.getById(id)
    }
}