package com.js.apps.moviedbapp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.js.apps.moviedbapp.domain.MediaTypes
import com.js.apps.moviedbapp.domain.media.MovieModel
import com.js.apps.moviedbapp.domain.media.SerieModel
import com.js.apps.moviedbapp.data.repository.implementation.MediaItemsRepositoryImp
import com.js.apps.moviedbapp.ui.core.CardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ItemsByCategoryViewModel @Inject constructor(
    val repository : MediaItemsRepositoryImp
): ViewModel() {
    val movieId = MutableLiveData<Int>()
    val serieId = MutableLiveData<Int>()
    val movies :MutableState<List<MovieModel>> = mutableStateOf(emptyList())
    val series :MutableSet<List<SerieModel>> = mutableSetOf(emptyList())

    init {
        viewModelScope.launch {
            movies.value = repository.discoverMovies()

        }
    }

     suspend fun discoverContents( type: MediaTypes):List<CardItem>{
         return withContext(Dispatchers.IO){
             repository.discoverContents(type)
         }
    }
    suspend fun getMostPopular( type: MediaTypes){
        return withContext(Dispatchers.IO){
            //repository.mostPopular(type)
        }
    }
    suspend fun getNowPlayingMovies(  ){
        return withContext(Dispatchers.IO){
            //repository.getNowPlaying()
        }
    }
    fun setMovieId(value:Int){
        movieId.value = value
    }
    fun setSerieId(value:Int){
        serieId.value = value
    }
}