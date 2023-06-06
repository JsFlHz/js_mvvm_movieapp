package com.js.apps.moviedbapp.domain

import com.js.apps.moviedbapp.domain.MediaTypes
import com.js.apps.moviedbapp.data.dasource.remotedatasource.response.MoviesResponse
import com.js.apps.moviedbapp.data.dasource.remotedatasource.response.SeriesResponse
import com.js.apps.moviedbapp.domain.media.MovieModel
import com.js.apps.moviedbapp.domain.media.SerieModel
import com.js.apps.moviedbapp.domain.media.VideoModel
import retrofit2.Response

interface RemoteDatasource {
    suspend fun discoverMovies(): Response<MoviesResponse>
    suspend fun discoverSeries(): Response<SeriesResponse>
    suspend fun getItemVideos( id:Int, type: MediaTypes):List<VideoModel>
    suspend fun getMostPopularMovies( ): List<MovieModel>
    suspend fun getMostPopularSeries( ): List<SerieModel>
    suspend fun getNowPlayingMovies( ): List<MovieModel>
}