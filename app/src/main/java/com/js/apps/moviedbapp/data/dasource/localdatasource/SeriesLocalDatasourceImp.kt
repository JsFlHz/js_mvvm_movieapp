package com.js.apps.moviedbapp.data.dasource.localdatasource

import androidx.lifecycle.LiveData
import com.js.apps.moviedbapp.domain.SeriesLDS
import com.js.apps.moviedbapp.data.dasource.localdatasource.room.dao.SeriesDao
import com.js.apps.moviedbapp.domain.media.SerieModel

class SeriesLocalDatasourceImp (
        private val dao: SeriesDao
    ): SeriesLDS {

    override suspend fun cleanAndSave(items: List<SerieModel>) {
        dao.deleteAll()
        dao.insert(items)
    }

    override fun getSeriesFromDB(): LiveData<List<SerieModel>> {
        return dao.getAll()
    }

    override suspend fun getSeriesFromDBSusp(): List<SerieModel> {
        return   dao.getAllSusp()
    }

}
