package com.js.apps.moviedbapp.model.repository.api.retrofit

import android.media.browse.MediaBrowser
import com.js.apps.moviedbapp.model.entities.api.response.DiscoverResponse
import com.js.apps.moviedbapp.model.entities.media.MediaItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApplicationAPIInterface {
    @GET("/3/discover/movie")
    //fun discoverMovies():Response<List<MediaItem>>
    suspend fun  discoverMovies(@Query("api_key") apiKey:String ):Response<DiscoverResponse>
    @GET("/3/discover/tv")
    fun discoverSeries():Call<DiscoverResponse>
    @GET("")
    suspend fun getItemDetail():Call<MediaItem>
}