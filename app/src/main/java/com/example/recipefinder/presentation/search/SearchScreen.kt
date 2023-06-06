package com.example.recipefinder.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.recipefinder.presentation.search.components.SearchBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier
) {
    var value by remember {
        mutableStateOf("")
    }
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        SearchBar(
            modifier.align(Alignment.TopCenter),
            searchQuery = value,
            onSearchQueryChange = { newValue ->
                value = newValue
            }
        )

    }

}