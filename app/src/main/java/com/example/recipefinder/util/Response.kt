package com.example.recipefinder.util

sealed class Response<T>(data : T? = null,message : String? = null) {
    data class Success<T>(val data: T?) : Response<T>(data = data)
    data class Error<T>(val message: String?) : Response<T>(message = message)
}

