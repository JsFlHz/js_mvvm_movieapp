package com.js.apps.moviedbapp.model.repository.api.retrofit

import com.js.apps.moviedbapp.model.repository.api.APIConstants
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