package com.js.apps.moviedbapp.data.dasource.remotedatasource.response

import com.js.apps.moviedbapp.domain.media.VideoModel


/***
 * @author Js <jose.flores.h222@gmail.com>
 * This class help us to translate the videos  service API response
 */
data class VideosResponse(
    var results: List<VideoModel>
)
data class VideoResponse(
    var id      : String,
    var key     : String,
    var site    : String
)