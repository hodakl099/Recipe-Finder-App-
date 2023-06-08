package com.example.recipefinder.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipefinder.presentation.search.components.ProductInfoItem
import com.example.recipefinder.presentation.search.components.SearchBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SearchBar(
            modifier.fillMaxWidth(),
            searchQuery = viewModel.searchValue,
            onSearchQueryChange = { newValue ->
                viewModel.onEvent(
                    SearchViewModel.SearchEvents.OnSearchEnter(newValue)
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
           items(state.value.products) { product ->
               ProductInfoItem(
                   modifier = Modifier.padding(16.dp),
                   product = product
               )
           }
        }

    }

}