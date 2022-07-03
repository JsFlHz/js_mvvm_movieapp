package com.js.apps.moviedbapp.model.repository.implementation

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.js.apps.moviedbapp.model.core.ConnectivityHelper
import com.js.apps.moviedbapp.model.core.MediaTypes
import com.js.apps.moviedbapp.model.entities.media.MediaItem
import com.js.apps.moviedbapp.model.repository.api.APIStatusCode
import com.js.apps.moviedbapp.model.repository.api.services.MediaItemsService
import com.js.apps.moviedbapp.model.repository.database.dao.MediaItemDao
import com.js.apps.moviedbapp.model.repository.database.room.AppDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MediaItemsRepository@Inject constructor(
    private val service :MediaItemsService,
    private val dao : MediaItemDao,
    private val connectivityHelper: ConnectivityHelper
){

    suspend fun discoverMovies():List<MediaItem>{
        if (connectivityHelper.isOnline()){
            return getMoviesFromService()
        }
        return emptyList()
    }

  private  suspend fun getMoviesFromService():List<MediaItem>{
      try{
        val response = service.discoverMovies(MediaTypes.MOVIE)
        Log.i("here response","${response.code()}")
        when(response.code()){
            APIStatusCode.API_KEY_ERROR.code ->{
                //do something
            }
            APIStatusCode.RESOURCE_NOT_FOUND.code->{
                //do something
            }
            APIStatusCode.SUCCES_CODE.code->{
                //save in database
                val items = response.body()?.results ?: emptyList()

                if(items.isNotEmpty()){
                    Log.i("here","saving ${response.body()!!.results.size} items")
                    for( item in items){
                        dao.insert(item)
                    }
                }
            }
        }
        Log.i("here","returning ${response.body()!!.results.size}")
        return response.body()?.results ?: emptyList()
      }catch (e:Exception){
          e.printStackTrace()
          return emptyList()
      }
    }
    fun getMediaItemsFromDB():LiveData<List<MediaItem>>{
        return dao.getAll()
    }


}