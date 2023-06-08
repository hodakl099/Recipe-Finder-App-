package com.example.recipefinder.data.repository

import com.example.recipefinder.data.remote.SpoonacularApi
import com.example.recipefinder.data.remote.model.Product
import com.example.recipefinder.domain.repository.SpoonacularRepo
import javax.inject.Inject

class SpoonacularRepoImpl @Inject constructor(
    private val api : SpoonacularApi
) : SpoonacularRepo {
    override suspend fun getProductsQuery(searchQuery: String): Result<List<Product>> {
        return try {
            val api = api.searchProductQuery(searchQuery)
            Result.success(api.results)
        } catch (e : Exception) {
            Result.failure(e)
        }
    }
}