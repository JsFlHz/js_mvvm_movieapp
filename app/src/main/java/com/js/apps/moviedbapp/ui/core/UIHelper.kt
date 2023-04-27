package com.js.apps.moviedbapp.ui.core

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.View
import android.webkit.WebView
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.js.apps.moviedbapp.R
import com.js.apps.moviedbapp.data.model.Video

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
                is WebView ->{
                    ( view  ).setBackgroundColor( color)
                }
            }
        }
    }
    fun createWebViewVideo(item:Video ): View{
        Log.i("ContentUICreator", "creating webview")
        var component:WebView? = null
        val layoutParams = LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT ).apply{
            gravity = Gravity.CENTER
        }

        if (Build.VERSION.SDK_INT in 21..22) {
            component =  WebView(context.applicationContext)
        }else{
            component = WebView(context)
        }
        component.getSettings().setSupportMultipleWindows(true);
        component.layoutParams = layoutParams
        setColor(R.color.black,component)
        var value = ""
        component.getSettings().javaScriptEnabled = true
            val custoHeightVideo =  300
            var description = ""


        value = "" +
                "<p>" +
                "<iframe " +
                " style=\"background-color:black;\""+
                "   src=\"https://www.youtube.com/embed/${item.key}?autoplay=1\"  " +
                "   allow=\"autoplay\" " +
                "   width=\"100%\" " +
                "   height=\"100%\" " +
                "   frameborder=\"0\" " +
                "   allowfullscreen=\"allowfullscreen\" " +
                "   data-mce-fragment=\"1\">" +
                "</iframe>" +
                "</p>"

        component.loadData( value,"text/html; charset=utf-8", "utf-8")
        return component
    }
}