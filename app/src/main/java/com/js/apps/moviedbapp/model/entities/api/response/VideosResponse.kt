package com.js.apps.moviedbapp.model.entities.api.response

import com.js.apps.moviedbapp.model.entities.media.Video

/***
 * @author Js <jose.flores.h222@gmail.com>
 * This class help us to translate the videos  service API response
 */
data class VideosResponse(
    var results: List<Video>
)
