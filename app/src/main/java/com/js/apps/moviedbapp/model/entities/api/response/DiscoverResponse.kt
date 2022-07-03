package com.js.apps.moviedbapp.model.entities.api.response

import com.js.apps.moviedbapp.model.entities.media.MediaItem

/***
 * @author Js <jose.flores.h222@gmail.com>
 * This class help us to translate the discover service API response
 */
data class DiscoverResponse(
    var results: List<MediaItem>
){

}
