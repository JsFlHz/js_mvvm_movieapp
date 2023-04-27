package com.js.apps.moviedbapp.ui.core

interface CardItem {
    fun cardId():Int
    fun getPoster():String
    fun getRating():String
    fun getName():String
    fun getDate():String
    fun getBackdrop():String
    fun getIntroText():String
    fun getAuthor():String
}