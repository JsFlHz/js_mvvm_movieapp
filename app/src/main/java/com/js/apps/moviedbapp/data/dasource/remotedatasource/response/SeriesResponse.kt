package com.js.apps.moviedbapp.data.dasource.remotedatasource.response

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.js.apps.moviedbapp.data.model.Serie

/***
 * @author Js <jose.flores.h222@gmail.com>
 * This class help us to translate the series discover service API response
 */
data class SeriesResponse   (
    var results: List<Serie>
)

data class SerieResponse(
    @PrimaryKey
    var id               : Int,
    @SerializedName("name")
    var title            : String,
    @SerializedName("backdrop_path")
    var backdropPath    : String,
    @SerializedName("original_language")
    var originalLanguage: String,
    @SerializedName("original_name")
    var originalName   : String,
    var overview         : String,
    var popularity       : Float,
    @SerializedName("poster_path")
    var posterPath      : String,
    @SerializedName("first_air_date")
    var releaseDate     : String,
    @SerializedName("vote_average")
    var voteAverage     : Float,
    @SerializedName("vote_count")
    var voteCount       : Int
)