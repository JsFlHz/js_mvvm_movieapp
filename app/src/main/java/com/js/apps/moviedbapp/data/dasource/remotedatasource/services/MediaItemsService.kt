package com.js.apps.moviedbapp.data.dasource.remotedatasource.services

import android.util.Log
import com.js.apps.moviedbapp.data.dasource.remotedatasource.APIConstants
import com.js.apps.moviedbapp.data.dasource.remotedatasource.response.MoviesResponse
import com.js.apps.moviedbapp.data.dasource.remotedatasource.response.SeriesResponse
import com.js.apps.moviedbapp.data.dasource.remotedatasource.retrofit.ApplicationAPIInterface
import com.js.apps.moviedbapp.data.dasource.remotedatasource.retrofit.RetrofitHelper
import com.js.apps.moviedbapp.data.model.Movie
import com.js.apps.moviedbapp.data.model.Serie
import com.js.apps.moviedbapp.data.model.Video
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MediaItemsService @Inject constructor(){
    private val retrofit = RetrofitHelper.getRetrofitHelper()

    suspend fun discoverMovies(): Response<MoviesResponse> {
        return withContext(Dispatchers.IO) {
            retrofit.create(ApplicationAPIInterface::class.java).discoverMovies(
                com.js.apps.moviedbapp.data.core.MediaTypes.MOVIE.path,
                APIConstants.TMDB_API_KEY.value,
                APIConstants.API_LAG_ESP_MX.value
            )
        }
    }
    suspend fun discoverSeries(): Response<SeriesResponse> {
        return withContext(Dispatchers.IO) {
            retrofit.create(ApplicationAPIInterface::class.java).discoverSeries(
                com.js.apps.moviedbapp.data.core.MediaTypes.SERIE.path,
                APIConstants.TMDB_API_KEY.value,
                APIConstants.API_LAG_ESP_MX.value
            )
        }
    }

    suspend fun getItemVideos( id:Int, type: com.js.apps.moviedbapp.data.core.MediaTypes): List<Video> {
        return withContext(Dispatchers.IO) {
         val  response =  retrofit.create(ApplicationAPIInterface::class.java).getItemVideos(
                type.path,
                id,
                APIConstants.TMDB_API_KEY.value,
                APIConstants.API_LAG_ESP_MX.value
            )
            response.body()?.results ?: emptyList()
        }
    }
    suspend fun getMostPopularMovies( ): List<Movie> {
        return withContext(Dispatchers.IO) {
            val  response =  retrofit.create(ApplicationAPIInterface::class.java).getMostPopularMovies(
                APIConstants.TMDB_API_KEY.value,
                APIConstants.API_LAG_ESP_MX.value
            )
            response.body()?.results ?: emptyList()
        }
    }
    suspend fun getMostPopularSeries( ): List<Serie> {
        return withContext(Dispatchers.IO) {
            val  response =  retrofit.create(ApplicationAPIInterface::class.java).getMostPopularSeries(
                APIConstants.TMDB_API_KEY.value,
                APIConstants.API_LAG_ESP_MX.value
            )
            response.body()?.results ?: emptyList()
        }
    }

    suspend fun getNowPlayingMovies( ): List<Movie> {
        return withContext(Dispatchers.IO) {
            val  response =  retrofit.create(ApplicationAPIInterface::class.java).getNowPlayingMovie(
                APIConstants.TMDB_API_KEY.value,
                APIConstants.API_LAG_ESP_MX.value
            )
            Log.i("here", "service getVideos ${response}")
            response.body()?.results ?: emptyList()
        }
    }

 }