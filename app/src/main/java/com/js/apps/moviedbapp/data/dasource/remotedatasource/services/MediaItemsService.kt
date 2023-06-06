package com.js.apps.moviedbapp.data.dasource.remotedatasource.services

import android.util.Log
import com.js.apps.moviedbapp.domain.MediaTypes
import com.js.apps.moviedbapp.domain.RemoteDatasource
import com.js.apps.moviedbapp.domain.APIConstants
import com.js.apps.moviedbapp.data.dasource.remotedatasource.response.MoviesResponse
import com.js.apps.moviedbapp.data.dasource.remotedatasource.response.SeriesResponse
import com.js.apps.moviedbapp.data.dasource.remotedatasource.retrofit.ApplicationAPIInterface
import com.js.apps.moviedbapp.domain.media.MovieModel
import com.js.apps.moviedbapp.domain.media.SerieModel
import com.js.apps.moviedbapp.domain.media.VideoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MediaItemsService @Inject constructor(
    private val applicationApi: ApplicationAPIInterface
) : RemoteDatasource {

    override suspend fun discoverMovies(): Response<MoviesResponse> {
        return withContext(Dispatchers.IO) {
            applicationApi.discoverMovies(
                MediaTypes.MOVIE.path,
                APIConstants.TMDB_API_KEY.value,
                APIConstants.API_LAG_ESP_MX.value
            )
        }
    }

    override suspend fun discoverSeries(): Response<SeriesResponse> {
        return withContext(Dispatchers.IO) {
            applicationApi.discoverSeries(
                MediaTypes.SERIE.path,
                APIConstants.TMDB_API_KEY.value,
                APIConstants.API_LAG_ESP_MX.value
            )
        }
    }

    override suspend fun getItemVideos(id: Int, type: MediaTypes): List<VideoModel> {
        return withContext(Dispatchers.IO) {
            val response = applicationApi.getItemVideos(
                type.path,
                id,
                APIConstants.TMDB_API_KEY.value,
                APIConstants.API_LAG_ESP_MX.value
            )
            response.body()?.results ?: emptyList()
        }
    }

    override suspend fun getMostPopularMovies(): List<MovieModel> {
        return withContext(Dispatchers.IO) {
            val response = applicationApi.getMostPopularMovies(
                APIConstants.TMDB_API_KEY.value,
                APIConstants.API_LAG_ESP_MX.value
            )
            response.body()?.results ?: emptyList()
        }
    }

    override suspend fun getMostPopularSeries(): List<SerieModel> {
        return withContext(Dispatchers.IO) {
            val response = applicationApi.getMostPopularSeries(
                APIConstants.TMDB_API_KEY.value,
                APIConstants.API_LAG_ESP_MX.value
            )
            response.body()?.results ?: emptyList()
        }
    }

    override suspend fun getNowPlayingMovies(): List<MovieModel> {
        return withContext(Dispatchers.IO) {
            val response = applicationApi.getNowPlayingMovie(
                APIConstants.TMDB_API_KEY.value,
                APIConstants.API_LAG_ESP_MX.value
            )
            Log.i("here", "service getVideos ${response}")
            response.body()?.results ?: emptyList()
        }
    }

}