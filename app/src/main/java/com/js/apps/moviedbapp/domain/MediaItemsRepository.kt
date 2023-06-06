package com.js.apps.moviedbapp.domain

import com.js.apps.moviedbapp.domain.media.MovieModel
import com.js.apps.moviedbapp.domain.media.SerieModel

interface MediaItemsRepository {
    suspend fun discoverMovies():List<MovieModel>
    suspend fun discoverSeries():List<SerieModel>
    //suspend fun getItemVideos( id:Int, type: MediaTypes)
    suspend fun getMostPopularMovies( ): List<MovieModel>
    suspend fun getMostPopularSeries( ): List<SerieModel>
    suspend fun getNowPlayingMovies( ): List<MovieModel>
}