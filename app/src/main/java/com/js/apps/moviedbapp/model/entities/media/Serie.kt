package com.js.apps.moviedbapp.model.entities.media

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.js.apps.moviedbapp.model.repository.api.APIConstants
import com.js.apps.moviedbapp.view.core.CardItem
import java.text.SimpleDateFormat

@Entity
data class Serie(
    @PrimaryKey
    var id               : Int,
    @SerializedName ("name")
    var title            : String,
    @SerializedName ("backdrop_path")
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
)  : CardItem  {
    override fun cardId(): Int {
        return id
    }

    override fun getPoster(): String {
        return "${APIConstants.IMAGES_SERVER_URL.value}${APIConstants.IMAGES_ORIGINAL_SIZE.value}$posterPath"
    }

    override fun getRating(): String {
        return "${getRatingPorcent(voteAverage)}%"
    }
    fun getRatingPorcent( voteAverage:Float ): Int {
        try{
            if(voteAverage>0){
                val value =(voteAverage*100)/10
                return value.toInt()
            }
        }catch (e:Exception){
            return 0
        }
        return 0
    }
    override fun getName(): String {
        return title
    }

    override fun getBackdrop(): String {
        return return "${APIConstants.IMAGES_SERVER_URL.value}${APIConstants.IMAGES_ORIGINAL_SIZE.value}$backdropPath"
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
   }

