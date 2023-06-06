package com.js.apps.moviedbapp.domain

import androidx.lifecycle.LiveData
import com.js.apps.moviedbapp.domain.media.MovieModel
import com.js.apps.moviedbapp.domain.media.SerieModel

sealed interface LocalDataSource {
    open val moviesDSInteractor: MoviesLDS
    open val seriesDSInteractor: SeriesLDS

}
interface GenericLDS<T>{
    suspend fun saveAll(items:List<T>)
    suspend fun getAll():List<T>
    suspend fun deleteAll()
    suspend fun save(item:T)
    suspend fun delete(item:T)
}

interface MoviesLDS{
    fun getMediaItemsFromDB( ): LiveData<List<MovieModel>>
    suspend fun cleanAndSave(items:List<MovieModel>)

    suspend fun getMoviesFromDBSusp( ):List<MovieModel>
}
interface SeriesLDS{
    suspend fun cleanAndSave(items:List<SerieModel>)
    suspend fun getSeriesFromDBSusp( ):List<SerieModel>
    fun getSeriesFromDB( ):LiveData<List<SerieModel>>

}