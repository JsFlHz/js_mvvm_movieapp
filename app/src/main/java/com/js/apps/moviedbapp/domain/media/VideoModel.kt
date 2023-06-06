package com.js.apps.moviedbapp.domain.media

data class VideoModel(
    var id      : String,
    var key     : String,
    var site    : String
)   {
    override fun toString(): String {
        return "Video(id=$id, key='$key', site='$site')"
    }
}

