package com.js.apps.moviedbapp.model.entities.media

data class Video(
    var id      : String,
    var key     : String,
    var site    : String
)   {
    override fun toString(): String {
        return "Video(id=$id, key='$key', site='$site')"
    }
}

