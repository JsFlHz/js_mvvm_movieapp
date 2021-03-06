package com.js.apps.moviedbapp.model.repository.api

enum class APIStatusCode(val code:Int) {
    SUCCES_CODE(200),
    API_KEY_ERROR(401),
    RESOURCE_NOT_FOUND(404)
}