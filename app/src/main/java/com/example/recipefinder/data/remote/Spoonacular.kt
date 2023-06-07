package com.example.recipefinder.data.remote

import com.example.recipefinder.data.remote.model.Product
import com.example.recipefinder.util.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Spoonacular {

    @GET("recipes/complexSearch")
    suspend fun searchProductQuery(
        @Query("query") query : String,
    ) : Response<List<Product>>
}