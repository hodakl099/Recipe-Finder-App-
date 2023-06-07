package com.example.recipefinder.domain.use_case

import com.example.recipefinder.data.remote.model.Product
import com.example.recipefinder.domain.repository.SpoonacularRepo
import retrofit2.Response
import java.util.concurrent.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repo : SpoonacularRepo) {

    suspend operator fun invoke(search : String) : Result<List<Product>>{
        if (search.isEmpty()) {
            return Result.success(emptyList())
        }
        val trimSearchQuery = search.trim()
        return repo.getProductsQuery(trimSearchQuery)
    }
}