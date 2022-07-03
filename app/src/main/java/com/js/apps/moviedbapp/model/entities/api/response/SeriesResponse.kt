package com.js.apps.moviedbapp.model.entities.api.response

import com.js.apps.moviedbapp.model.entities.media.Serie

/***
 * @author Js <jose.flores.h222@gmail.com>
 * This class help us to translate the series discover service API response
 */
data class SeriesResponse   (
    var results: List<Serie>
)