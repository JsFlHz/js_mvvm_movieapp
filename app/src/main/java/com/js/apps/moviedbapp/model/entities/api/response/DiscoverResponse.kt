package com.js.apps.moviedbapp.model.entities.api.response

import com.js.apps.moviedbapp.model.entities.media.MediaItem

data class DiscoverResponse(
    var results: List<MediaItem>
){

}
