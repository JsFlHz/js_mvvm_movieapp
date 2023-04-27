package com.js.apps.moviedbapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.js.apps.moviedbapp.data.repository.implementation.MediaItemsRepositoryImp
import com.js.apps.moviedbapp.ui.core.CardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ItemsByCategoryViewModel @Inject constructor(
    val repository : MediaItemsRepositoryImp
): ViewModel() {
    val movieId = MutableLiveData<Int>()
    val serieId = MutableLiveData<Int>()
    var allMovies  = Transformations.switchMap(movieId){ repository.getMediaItemsFromDB() }
    var allSeries  = Transformations.switchMap(serieId){repository.getSeriesFromDB()}

     suspend fun discoverContents( type: com.js.apps.moviedbapp.data.core.MediaTypes):List<CardItem>{
         return withContext(Dispatchers.IO){
             repository.discoverContents(type)
         }
    }
    suspend fun getMostPopular( type: com.js.apps.moviedbapp.data.core.MediaTypes){
        return withContext(Dispatchers.IO){
            repository.mostPopular(type)
        }
    }
    suspend fun getNowPlayingMovies(  ){
        return withContext(Dispatchers.IO){
            repository.getNowPlaying()
        }
    }
    fun setMovieId(value:Int){
        movieId.value = value
    }
    fun setSerieId(value:Int){
        serieId.value = value
    }
}