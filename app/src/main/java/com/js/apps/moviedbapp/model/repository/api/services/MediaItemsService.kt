package com.js.apps.moviedbapp.model.repository.api.services

import com.js.apps.moviedbapp.model.core.MediaTypes
import com.js.apps.moviedbapp.model.entities.api.response.MoviesResponse
import com.js.apps.moviedbapp.model.entities.api.response.SeriesResponse
import com.js.apps.moviedbapp.model.entities.media.Movie
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

    suspend fun getItemDeatail( id:Int): Response<Movie> {
        return withContext(Dispatchers.IO) {
            retrofit.create(ApplicationAPIInterface::class.java).getItemDetail(
                id,
                APIConstants.TMDB_API_KEY.value,
                APIConstants.API_LAG_ESP_MX.value
            )
        }
    }
 }