package com.js.apps.moviedbapp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.js.apps.moviedbapp.domain.media.MovieModel
import com.js.apps.moviedbapp.domain.media.SerieModel
import com.js.apps.moviedbapp.domain.media.VideoModel
import com.js.apps.moviedbapp.data.repository.implementation.ItemRepositoryImp
import com.js.apps.moviedbapp.domain.MediaTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ItemDetailViewModel  @Inject constructor( private val repository: ItemRepositoryImp) : ViewModel() {
    val currentMovie: MutableState<MovieModel> = mutableStateOf(MovieModel())
    val currentSerie: MutableState<SerieModel> = mutableStateOf(SerieModel())
    suspend fun getVideos(id:Int, type: MediaTypes) : List<VideoModel>{
        return withContext(Dispatchers.IO){
            repository.getItemVideos(id,type)
        }
    }
}