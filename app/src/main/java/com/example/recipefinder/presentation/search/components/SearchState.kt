package com.example.recipefinder.presentation.search.components

import com.example.recipefinder.data.remote.model.Product

data class SearchState(
    val products : List<Product>,
    val loading : Boolean = false
)
