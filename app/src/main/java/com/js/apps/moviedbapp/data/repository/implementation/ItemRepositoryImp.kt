package com.js.apps.moviedbapp.data.repository.implementation

import androidx.lifecycle.LiveData
import com.js.apps.moviedbapp.data.core.ConnectivityHelper
import com.js.apps.moviedbapp.domain.MediaTypes
import com.js.apps.moviedbapp.data.dasource.localdatasource.room.dao.MoviesDao
import com.js.apps.moviedbapp.data.dasource.localdatasource.room.dao.SeriesDao
import com.js.apps.moviedbapp.data.dasource.remotedatasource.services.MediaItemsService
import com.js.apps.moviedbapp.domain.media.MovieModel
import com.js.apps.moviedbapp.domain.media.SerieModel
import com.js.apps.moviedbapp.domain.media.VideoModel
import com.js.apps.moviedbapp.data.repository.ItemRepository

import javax.inject.Inject

class ItemRepositoryImp @Inject constructor(
    private val service :MediaItemsService,
    private val moviesDao : MoviesDao,
    private val seriesDao:  SeriesDao,
    private val connectivityHelper: ConnectivityHelper
){
    suspend fun getItemVideos(id:Int, type: MediaTypes):List<VideoModel>{
        return service.getItemVideos(id, type)
    }
    fun getMovieItemFromDB( id:Int ):LiveData<MovieModel>{
        return moviesDao.getById(id)
    }
    fun getSerieItemFromDB( id:Int ):LiveData<SerieModel>{
        return seriesDao.getById(id)
    }
}
