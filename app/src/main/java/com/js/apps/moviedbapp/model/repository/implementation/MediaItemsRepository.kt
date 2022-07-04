package com.js.apps.moviedbapp.model.repository.implementation

import android.util.Log
import androidx.lifecycle.LiveData
import com.js.apps.moviedbapp.model.core.ConnectivityHelper
import com.js.apps.moviedbapp.model.core.MediaTypes
import com.js.apps.moviedbapp.model.entities.media.Movie
import com.js.apps.moviedbapp.model.entities.media.Serie
import com.js.apps.moviedbapp.model.repository.api.APIStatusCode
import com.js.apps.moviedbapp.model.repository.api.services.MediaItemsService
import com.js.apps.moviedbapp.model.repository.database.dao.MoviesDao
import com.js.apps.moviedbapp.model.repository.database.dao.SeriesDao
import com.js.apps.moviedbapp.view.core.CardItem
import javax.inject.Inject

class MediaItemsRepository@Inject constructor(
    private val service :MediaItemsService,
    private val moviesDao : MoviesDao,
    private val seriesDao:SeriesDao,
    private val connectivityHelper: ConnectivityHelper
){

    suspend fun discoverContents( type:MediaTypes):List<CardItem>{
       return when(type){
           MediaTypes.SERIE->discoverSeries()
            MediaTypes.MOVIE->discoverMovies()
        }
    }
    private suspend fun discoverMovies():List<Movie>{
          if (connectivityHelper.isOnline()){
              try{
                  val response = service.discoverMovies()
                  Log.i("here response","${response.code()}")
                  when(response.code()){
                      APIStatusCode.SUCCES_CODE.code->{
                          //save in database
                          val items = response.body()?.results ?: emptyList()
                          if(items.isNotEmpty()){
                              moviesDao.deleteAll()
                              for( item in items){
                                  moviesDao.insert(item)
                              }
                          }
                      }
                  }
                  return response.body()?.results ?: emptyList()
              }catch (e:Exception){
                  e.printStackTrace()
                  return emptyList()
              }
          }else{
            return getMoviesFromDBSusp()
          }
      }
      private suspend fun discoverSeries():List<Serie>{
            if (connectivityHelper.isOnline()){
                try{
                    val response = service.discoverSeries()
                    Log.i("here response","${response.code()}")
                    when(response.code()){
                        APIStatusCode.SUCCES_CODE.code->{
                            //save in database
                            val items = response.body()?.results ?: emptyList()

                            if(items.isNotEmpty()){
                                for( item in items){
                                    seriesDao.insert(item)
                                }
                            }
                        }
                    }
                    return response.body()?.results ?: emptyList()
                }catch (e:Exception){
                    e.printStackTrace()
                    return emptyList()
                }
            }else{
                return getSeriesFromDBSusp()
            }
      }

    suspend fun mostPopular( type:MediaTypes){
        return when(type){
            MediaTypes.SERIE->getMostPopularSeries()
            MediaTypes.MOVIE->getMostPopularMovies()
        }
    }
    private suspend fun getMostPopularMovies(){
            try{
                val response = service.getMostPopularMovies()
                if( response.isNotEmpty()){
                    moviesDao.deleteAll()
                    for(item in response){
                        moviesDao.insert(item)
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
    }

    private suspend fun getMostPopularSeries(){
        try{
            val response = service.getMostPopularSeries()
            if( response.isNotEmpty()){
                seriesDao.deleteAll()
                for(item in response){
                    seriesDao.insert(item)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

     suspend fun getNowPlaying(){
        try{
            val response = service.getNowPlayingMovies()
            if( response.isNotEmpty()){
                moviesDao.deleteAll()
                for(item in response){
                    moviesDao.insert(item)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun getMediaItemsFromDB( ):LiveData<List<Movie>>{
        return moviesDao.getAll()
    }

    fun getSeriesFromDB( ):LiveData<List<Serie>>{
        return seriesDao.getAll()
    }
    suspend fun getMoviesFromDBSusp( ):List<Movie>{
        return moviesDao.getAllSusp()
    }
    suspend fun getSeriesFromDBSusp( ):List<Serie>{
        return seriesDao.getAllSusp()
    }



}