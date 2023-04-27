package com.js.apps.moviedbapp.data.dasource

import com.js.apps.moviedbapp.data.model.Movie
import com.js.apps.moviedbapp.data.model.Serie

interface LocalDataSource {
    suspend fun getAllMovies():List<Movie>
    suspend fun getAllSeries():List<Serie>
}