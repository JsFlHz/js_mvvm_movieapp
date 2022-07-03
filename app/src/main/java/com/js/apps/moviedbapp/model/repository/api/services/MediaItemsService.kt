package com.js.apps.moviedbapp.model.repository.api.services

import com.js.apps.moviedbapp.model.entities.api.response.DiscoverResponse
import com.js.apps.moviedbapp.model.entities.media.MediaItem
import com.js.apps.moviedbapp.model.repository.api.APIConstants
import com.js.apps.moviedbapp.model.repository.api.retrofit.ApplicationAPIInterface
import com.js.apps.moviedbapp.model.repository.api.retrofit.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class MediaItemsService{
    private val retrofit = RetrofitHelper.getRetrofitHelper()

    suspend fun discoverMovies(): Response<DiscoverResponse> {
        return withContext(Dispatchers.IO) {
            retrofit.create(ApplicationAPIInterface::class.java).discoverMovies(APIConstants.TMDB_API_KEY.value)
        }
    }
}