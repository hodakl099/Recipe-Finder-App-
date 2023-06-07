package com.example.recipefinder.data.remote

import com.example.recipefinder.data.remote.model.Product
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApi {
    @GET("recipes/complexSearch")
    suspend fun searchProductQuery(
        @Query("query") query : String,
    ) : List<Product>
}