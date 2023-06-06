package com.js.apps.moviedbapp.di

import com.js.apps.moviedbapp.domain.APIConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit():Retrofit =
        Retrofit.Builder()
            .baseUrl(APIConstants.TMDB_SERVER_URL.value)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}