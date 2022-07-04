package com.js.apps.moviedbapp.model.repository.api.services

import android.util.Log
import com.js.apps.moviedbapp.model.core.MediaTypes
import com.js.apps.moviedbapp.model.entities.api.response.MoviesResponse
import com.js.apps.moviedbapp.model.entities.api.response.SeriesResponse
import com.js.apps.moviedbapp.model.entities.media.Movie
import com.js.apps.moviedbapp.model.entities.media.Serie
import com.js.apps.moviedbapp.model.entities.media.Video
import com.js.apps.moviedbapp.model.repository.api.APIConstants
import com.js.apps.moviedbapp.model.repository.api.retrofit.ApplicationAPIInterface
import com.js.apps.moviedbapp.model.repository.api.retrofit.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MediaItemsService @Inject constructor(){
    private val retrofit = RetrofitHelper.getRetrofitHelper()

    suspend fun discoverMovies(): Response<MoviesResponse> {
        return withContext(Dispatchers.IO) {
            retrofit.create(ApplicationAPIInterface::class.java).discoverMovies(
                MediaTypes.MOVIE.path,
                APIConstants.TMDB_API_KEY.value,
                APIConstants.API_LAG_ESP_MX.value
            )
        }
    }
    suspend fun discoverSeries(): Response<SeriesResponse> {
        return withContext(Dispatchers.IO) {
            retrofit.create(ApplicationAPIInterface::class.java).discoverSeries(
                MediaTypes.SERIE.path,
                APIConstants.TMDB_API_KEY.value,
                APIConstants.API_LAG_ESP_MX.value
            )
        }
    }

    suspend fun getItemVideos( id:Int, type:MediaTypes): List<Video> {
        Log.i("here", "service getVideos")
        return withContext(Dispatchers.IO) {
         val  response =  retrofit.create(ApplicationAPIInterface::class.java).getItemVideos(
                type.path,
                id,
                APIConstants.TMDB_API_KEY.value,
                APIConstants.API_LAG_ESP_MX.value
            )
            Log.i("here", "service getVideos ${response}")
            response.body()?.results ?: emptyList()
        }
    }
    suspend fun getMostPopularMovies( ): List<Movie> {
        Log.i("here", "service getVideos")
        return withContext(Dispatchers.IO) {
            val  response =  retrofit.create(ApplicationAPIInterface::class.java).getMostPopularMovies(
                APIConstants.TMDB_API_KEY.value,
                APIConstants.API_LAG_ESP_MX.value
            )
            Log.i("here", "service getVideos ${response}")
            response.body()?.results ?: emptyList()
        }
    }
    suspend fun getMostPopularSeries( ): List<Serie> {
        Log.i("here", "service getVideos")
        return withContext(Dispatchers.IO) {
            val  response =  retrofit.create(ApplicationAPIInterface::class.java).getMostPopularSeries(
                APIConstants.TMDB_API_KEY.value,
                APIConstants.API_LAG_ESP_MX.value
            )
            Log.i("here", "service getVideos ${response}")
            response.body()?.results ?: emptyList()
        }
    }

    suspend fun getNowPlayingMovies( ): List<Movie> {
        Log.i("here", "service getVideos")
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