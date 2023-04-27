package com.js.apps.moviedbapp.data.dasource.remotedatasource.retrofit

import com.js.apps.moviedbapp.data.dasource.remotedatasource.APIConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofitHelper():Retrofit{
        return Retrofit.Builder()
            .baseUrl(APIConstants.TMDB_SERVER_URL.value)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}