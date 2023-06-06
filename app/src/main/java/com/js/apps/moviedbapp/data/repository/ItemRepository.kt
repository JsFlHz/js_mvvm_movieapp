package com.js.apps.moviedbapp.data.repository

import android.provider.MediaStore.Video
import com.js.apps.moviedbapp.domain.MediaTypes


interface ItemRepository {
    suspend fun getItemTrailers(id:Int, type: MediaTypes):List<Video>

}
