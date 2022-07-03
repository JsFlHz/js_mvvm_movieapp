package com.js.apps.moviedbapp.model.entities.api.response

import com.js.apps.moviedbapp.model.entities.media.Movie

/***
 * @author Js <jose.flores.h222@gmail.com>
 * This class help us to translate the movies discover service API response
 */
data class MoviesResponse(
    var results: List<Movie>
)
