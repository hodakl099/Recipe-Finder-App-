package com.example.recipefinder.domain.repository

import kotlinx.coroutines.flow.Flow

interface SpoonacularRepo {


    suspend fun getProductsQuery(searchQuery : String) : Flow<List<DomainProduct>>
}