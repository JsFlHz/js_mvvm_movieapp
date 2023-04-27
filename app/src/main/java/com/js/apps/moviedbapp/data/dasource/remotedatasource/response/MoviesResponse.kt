package com.js.apps.moviedbapp.data.dasource.remotedatasource.response

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.js.apps.moviedbapp.data.model.Movie

/***
 * @author Js <jose.flores.h222@gmail.com>
 * This class help us to translate the movies discover service API response
 */
data class MoviesResponse(
    var results: List<Movie>
)
data class MovieResponse (
    @PrimaryKey()
    var id               : Int,
    var adult            : Boolean,
    @SerializedName("backdrop_path")
    var backdropPath    : String,
    @SerializedName("original_language")
    var originalLanguage: String,
    @SerializedName("original_title")
    var originalTitle   : String,
    var overview         : String,
    var popularity       : Float,
    @SerializedName("poster_path")
    var posterPath      : String,
    @SerializedName("release_date")
    var releaseDate     : String,
    var title            : String,
    var video            : Boolean,
    @SerializedName("vote_average")
    var voteAverage     : Float,
    @SerializedName("vote_count")
    var voteCount       : Int,
)

