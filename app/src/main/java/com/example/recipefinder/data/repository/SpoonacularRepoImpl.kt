package com.example.recipefinder.data.repository

import com.example.recipefinder.data.remote.Spoonacular
import com.example.recipefinder.data.remote.model.Product
import com.example.recipefinder.domain.repository.SpoonacularRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SpoonacularRepoImpl @Inject constructor(
    private val api : Spoonacular
) : SpoonacularRepo {

    override suspend fun getProductsQuery(searchQuery: String): Flow<List<Product>> {
     return api.searchProductQuery(searchQuery)
    }
}