package com.js.apps.moviedbapp.viewmodel.features

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.js.apps.moviedbapp.model.core.MediaTypes
import com.js.apps.moviedbapp.model.entities.media.Video
import com.js.apps.moviedbapp.model.repository.implementation.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ItemDetailViewModel  @Inject constructor( private val repository: ItemRepository) : ViewModel() {
    private val videoId = MutableLiveData<Int>()
    private val serieId = MutableLiveData<Int>()
    val currentMovie = Transformations.switchMap( videoId ) {
        repository.getMovieItemFromDB(it)
    }
    val currentSerie = Transformations.switchMap(serieId){
        repository.getSerieItemFromDB(it)
    }
    fun setVideoId( value:Int ){
        videoId.value = value
    }
    fun setSerieId( value:Int ){
        serieId.value = value
    }
    suspend fun getVideos(id:Int, type:MediaTypes) : List<Video>{
        return withContext(Dispatchers.IO){
            repository.getItemVideos(id,type)
        }
    }
}