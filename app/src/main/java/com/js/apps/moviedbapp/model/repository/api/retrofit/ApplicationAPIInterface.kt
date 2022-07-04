package com.js.apps.moviedbapp.model.repository.api.retrofit

import com.js.apps.moviedbapp.model.entities.api.response.MoviesResponse
import com.js.apps.moviedbapp.model.entities.api.response.SeriesResponse
import com.js.apps.moviedbapp.model.entities.api.response.VideosResponse
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

    @GET("/3/{category}/{id}/videos")
    suspend fun getItemVideos(
        @Path("category") category:String,
        @Path("id") id:Int,
        @Query("api_key") apiKey:String,
        @Query("language") lang:String
    ):Response<VideosResponse>

    @GET("/3/movie/popular")
    suspend fun getMostPopularMovies(
        @Query("api_key") apiKey:String,
        @Query("language") lang:String
    ):Response<MoviesResponse>

    @GET("/3/tv/popular")
    suspend fun getMostPopularSeries(
        @Query("api_key") apiKey:String,
        @Query("language") lang:String
    ):Response<SeriesResponse>

    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") apiKey:String,
        @Query("language") lang:String
    ):Response<MoviesResponse>
}