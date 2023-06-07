package com.example.recipefinder.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipefinder.presentation.search.components.ProductInfoItem
import com.example.recipefinder.presentation.search.components.SearchBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier
) {
    var value by remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SearchBar(
            modifier.fillMaxWidth(),
            searchQuery = value,
            onSearchQueryChange = { newValue ->
                value = newValue
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
           items(10) {
               ProductInfoItem(
                   modifier = Modifier.padding(16.dp)
               )
           }
        }

    }

}