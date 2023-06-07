package com.example.recipefinder.domain.repository

import com.example.recipefinder.data.remote.model.Product
import com.example.recipefinder.util.Response

interface SpoonacularRepo {

    suspend fun getProductsQuery(searchQuery : String) : Result<List<Product>>
}