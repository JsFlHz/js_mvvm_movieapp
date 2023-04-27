package com.js.apps.moviedbapp.data.model

data class Video(
    var id      : String,
    var key     : String,
    var site    : String
)   {
    override fun toString(): String {
        return "Video(id=$id, key='$key', site='$site')"
    }
}

