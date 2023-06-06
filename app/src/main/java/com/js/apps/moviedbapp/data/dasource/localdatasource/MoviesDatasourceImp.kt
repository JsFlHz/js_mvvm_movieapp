package com.js.apps.moviedbapp.data.dasource.localdatasource

import androidx.lifecycle.LiveData
import com.js.apps.moviedbapp.domain.MoviesLDS
import com.js.apps.moviedbapp.data.dasource.localdatasource.room.dao.MoviesDao
import com.js.apps.moviedbapp.domain.media.MovieModel
import javax.inject.Inject

class MoviesDatasourceImp @Inject constructor(
    private val dao:MoviesDao
): MoviesLDS {

    override suspend fun cleanAndSave(items: List<MovieModel>) {
        dao.deleteAll()
        dao.insert(items)
    }

    override fun getMediaItemsFromDB(): LiveData<List<MovieModel>> {
      return   dao.getAll()
    }
    override suspend fun getMoviesFromDBSusp(): List<MovieModel> {
        return dao.getAllSusp()
    }
}