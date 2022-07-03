package com.js.apps.moviedbapp.model.repository.implementation

import android.util.Log
import com.js.apps.moviedbapp.model.entities.media.MediaItem
import com.js.apps.moviedbapp.model.repository.api.APIStatusCode
import com.js.apps.moviedbapp.model.repository.api.services.MediaItemsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MediaItemsRepository {
    private val service = MediaItemsService()
    suspend fun discoverMovies():List<MediaItem>{
        val response = service.discoverMovies()
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


            }
        }
        Log.i("here","${response.body()!!.results.size}")
        return response.body()?.results ?: emptyList()
    }
    fun discoverSeries(){

    }
    fun getIemDetail( itemId:Int){

    }


}