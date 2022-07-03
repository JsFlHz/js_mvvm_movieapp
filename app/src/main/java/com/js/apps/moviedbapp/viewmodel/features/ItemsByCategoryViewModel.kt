package com.js.apps.moviedbapp.viewmodel.features

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.js.apps.moviedbapp.model.entities.api.response.DiscoverResponse
import com.js.apps.moviedbapp.model.entities.media.MediaItem
import com.js.apps.moviedbapp.model.repository.implementation.MediaItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class ItemsByCategoryViewModel @Inject constructor(
    val repository : MediaItemsRepository
): ViewModel() {
    var allMediaItems :LiveData<List<MediaItem>> = repository.getMediaItemsFromDB()
     suspend fun discoverMovies():List<MediaItem>{
         return withContext(Dispatchers.IO){
             repository.discoverMovies()
         }
    }
}