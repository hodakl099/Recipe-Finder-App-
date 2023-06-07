package com.example.recipefinder.domain.repository

import com.example.recipefinder.domain.DomainProduct
import kotlinx.coroutines.flow.Flow

interface SpoonacularRepo {


    suspend fun getProductsQuery() : Flow<List<DomainProduct>>
}