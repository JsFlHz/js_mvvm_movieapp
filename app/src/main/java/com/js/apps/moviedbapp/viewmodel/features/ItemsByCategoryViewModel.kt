package com.js.apps.moviedbapp.viewmodel.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.js.apps.moviedbapp.model.core.MediaTypes
import com.js.apps.moviedbapp.model.entities.media.Movie
import com.js.apps.moviedbapp.model.entities.media.Serie
import com.js.apps.moviedbapp.model.repository.implementation.MediaItemsRepository
import com.js.apps.moviedbapp.view.core.CardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ItemsByCategoryViewModel @Inject constructor(
    val repository : MediaItemsRepository
): ViewModel() {
    val movieId = MutableLiveData<Int>()
    val serieId = MutableLiveData<Int>()
    var allMovies  = Transformations.switchMap(movieId){ repository.getMediaItemsFromDB() }
    var allSeries  = Transformations.switchMap(serieId){repository.getSeriesFromDB()}

     suspend fun discoverContents( type:MediaTypes):List<CardItem>{
         return withContext(Dispatchers.IO){
             repository.discoverContents(type)
         }
    }
    fun setMovieId(value:Int){
        movieId.value = value
    }
    fun setSerieId(value:Int){
        serieId.value = value
    }
}