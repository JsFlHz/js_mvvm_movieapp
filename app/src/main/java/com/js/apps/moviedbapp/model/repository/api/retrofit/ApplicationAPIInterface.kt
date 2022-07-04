package com.js.apps.moviedbapp.model.repository.api.retrofit

import com.js.apps.moviedbapp.model.entities.api.response.MoviesResponse
import com.js.apps.moviedbapp.model.entities.api.response.SeriesResponse
import com.js.apps.moviedbapp.model.entities.media.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApplicationAPIInterface {
    @GET("/3/discover/{category}")
    suspend fun  discoverMovies(
        @Path("category") category:String,
        @Query("api_key") apiKey:String,
        @Query("language") lang:String
    ):Response<MoviesResponse>

    @GET("/3/discover/{category}")
    suspend fun  discoverSeries(
        @Path("category") category:String,
        @Query("api_key") apiKey:String,
        @Query("language") lang:String
    ):Response<SeriesResponse>

    @GET("")
    suspend fun getItemDetail(
        @Query("id") id:Int ,
        @Query("api_key") apiKey:String,
        @Query("language") lang:String
    ):Response<Movie>
}