package com.js.apps.moviedbapp.data.dasource.localdatasource.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.js.apps.moviedbapp.domain.APIConstants
import com.js.apps.moviedbapp.ui.core.CardItem
import java.text.SimpleDateFormat

@Entity
data class Serie(
    @PrimaryKey
    var id               : Int = 0,
    var title            : String = "",
    var backdropPath    : String = "",
    var originalLanguage: String = "",
    var originalName   : String = "",
    var overview         : String = "",
    var popularity       : Float = 0F,
    var posterPath      : String = "",
    var releaseDate     : String = "",
    var voteAverage     : Float = 0F,
    var voteCount       : Int = 0
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

