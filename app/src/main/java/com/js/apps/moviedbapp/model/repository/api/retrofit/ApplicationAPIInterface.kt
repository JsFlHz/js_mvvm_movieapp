package com.js.apps.moviedbapp.model.repository.api.retrofit

import android.media.browse.MediaBrowser
import com.js.apps.moviedbapp.model.entities.api.response.DiscoverResponse
import com.js.apps.moviedbapp.model.entities.media.MediaItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApplicationAPIInterface {
    @GET("/3/{categroy}/movie")
    //fun discoverMovies():Response<List<MediaItem>>
    suspend fun  discoverMovies(@Path("category") category:String, @Query("api_key") apiKey:String ):Response<DiscoverResponse>

    @GET("")
    suspend fun getItemDetail(@Query("id") id:Int ,@Query("api_key")apiKey:String ):Response<MediaItem>
}