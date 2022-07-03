package com.js.apps.moviedbapp.model.repository.api

/***
 *  @author Js <jose.flores.h222@gmail.com>
 *  This enum class defines  all api constants needed
 */
enum class APIConstants(val value:String) {
    TMDB_SERVER_URL("https://api.themoviedb.org/"),
    IMAGES_ORIGINAL_SIZE("w300/"),
    IMAGES_SERVER_URL("https://image.tmdb.org/t/p/"),
    TMDB_API_KEY("8c631fa0f74af66c3dd2ba6663b43c5c")
}