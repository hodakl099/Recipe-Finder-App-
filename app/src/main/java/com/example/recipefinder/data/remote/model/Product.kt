package com.example.recipefinder.data.remote.model

import retrofit2.http.Field

data class Product(
    val id : Int,
    val title : String,
    val image : String,
)
