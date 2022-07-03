package com.js.apps.moviedbapp.view.core

import android.app.Application
import androidx.recyclerview.widget.RecyclerView

class UIHelper(val context:Application) {
    fun createRecyclerView(items: List<CardItem>):RecyclerView{
       val recyclerView = RecyclerView(context)
        val adapter = CardItemAdapter(items){

        }
        return recyclerView
    }
}