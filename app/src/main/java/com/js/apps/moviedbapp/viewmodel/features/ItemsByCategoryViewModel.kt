package com.js.apps.moviedbapp.viewmodel.features

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.js.apps.moviedbapp.model.entities.api.response.DiscoverResponse
import com.js.apps.moviedbapp.model.entities.media.MediaItem
import com.js.apps.moviedbapp.model.repository.implementation.MediaItemsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemsByCategoryViewModel : ViewModel() {
    val repository =  MediaItemsRepository()
     suspend fun discoverMovies():List<MediaItem>{
         return withContext(Dispatchers.IO){
             repository.discoverMovies()
         }
    }
}