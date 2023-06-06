package com.js.apps.moviedbapp.data.dasource

import com.js.apps.moviedbapp.domain.LocalDataSource
import com.js.apps.moviedbapp.domain.MoviesLDS
import com.js.apps.moviedbapp.domain.SeriesLDS

class LocalDatasourceInteractor(
    val seriesLDS: SeriesLDS,
    val moviesLDS: MoviesLDS
)//: LocalDataSource {
//    override val moviesDSInteractor: MoviesLDS
//        get() = moviesLDS
//    override val seriesDSInteractor: SeriesLDS
//        get() = seriesLDS
//}