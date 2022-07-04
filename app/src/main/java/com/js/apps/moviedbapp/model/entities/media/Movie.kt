package com.js.apps.moviedbapp.model.entities.media

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.js.apps.moviedbapp.model.repository.api.APIConstants
import com.js.apps.moviedbapp.view.core.CardItem
import java.text.SimpleDateFormat

@Entity
data class Movie(
    @PrimaryKey()
    var id               : Int,
    var adult            : Boolean,
    @SerializedName ("backdrop_path")
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
)  : CardItem  {
    override fun cardId():Int{
        return id
    }
    override fun getPoster(): String {
        return "${APIConstants.IMAGES_SERVER_URL.value}${APIConstants.IMAGES_ORIGINAL_SIZE.value}$posterPath"
    }

    override fun getRating(): String {
        return "${voteAverage}"
    }

    override fun getName(): String {
        return title
    }

    override fun getBackdrop(): String {
        return  return "${APIConstants.IMAGES_SERVER_URL.value}${APIConstants.IMAGES_ORIGINAL_SIZE.value}$backdropPath"
    }

    override fun getIntroText(): String {
        return overview
    }

    override fun getAuthor(): String {
        return ""
    }

    override fun getDate(): String {
       return if( releaseDate.isNotEmpty()){

           val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
           val date = simpleDateFormat.parse(releaseDate)
           val newDateFormat = SimpleDateFormat("dd MMM yyyy")
           newDateFormat.format(date)

        }else{
          releaseDate
        }
    }

    override fun toString(): String {
        return "MediaItem(id=$id, adult=$adult, backdropPath='$backdropPath', originalLanguage='$originalLanguage', originalTitle='$originalTitle', overview='$overview', popularity=$popularity, posterPath='$posterPath', releaseDate='$releaseDate', title='$title', video=$video, voteAverage=$voteAverage, voteCount=$voteCount)"
    }

}