package com.js.apps.moviedbapp.model.repository.api.retrofit

import android.media.browse.MediaBrowser
import com.js.apps.moviedbapp.model.entities.api.response.DiscoverResponse
import com.js.apps.moviedbapp.model.entities.media.MediaItem
import retrofit2.Call
import retrofit2.http.GET

interface ApplicationAPIInterface {
    @GET("/discover/movie")
    fun discoverMovies():Call<DiscoverResponse>
    @GET("/discover/tv")
    fun discoverSeries():Call<DiscoverResponse>
    @GET("")
    fun getItemDetail():Call<MediaItem>
}