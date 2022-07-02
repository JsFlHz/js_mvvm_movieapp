package com.js.apps.moviedbapp.model.entities.media
import com.google.gson.annotations.SerializedName
import com.js.apps.moviedbapp.model.repository.api.APIConstants

data class MediaItem(
    var id               : Int,
    var adult            : Boolean,
    @SerializedName ("backdrop_path")
    var backdropPath    : String,
    //var genre_ids        :  // [3 items],
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

)    {
 fun getPosterURL():String{
     return "${APIConstants.IMAGES_SERVER_URL.value}${APIConstants.IMAGES_ORIGINAL_SIZE.value}$posterPath"
 }
 override fun toString(): String {
        return "MediaItem(id=$id, adult=$adult, backdropPath='$backdropPath', originalLanguage='$originalLanguage', originalTitle='$originalTitle', overview='$overview', popularity=$popularity, posterPath='$posterPath', releaseDate='$releaseDate', title='$title', video=$video, voteAverage=$voteAverage, voteCount=$voteCount)"
    }

}