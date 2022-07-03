package com.js.apps.moviedbapp.view.core

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class UIHelper(val context:Context) {
    fun setColor( color:Int, vararg views: View){
        val color =  ContextCompat.getColor( context, color )
        for(view  in views){
            when (view){
                is TextView ->{
                    ( view  ).setTextColor( color )
                }
                is EditText ->{
                    ( view  ).setTextColor( color)
                }
                is LinearLayout ->{
                    ( view  ).setBackgroundColor( color)
                }
            }
        }
    }
}