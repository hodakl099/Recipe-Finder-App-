package com.example.recipefinder.presentation.search

import android.view.View.MeasureSpec
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipefinder.presentation.search.components.ProductInfoItem
import com.example.recipefinder.presentation.search.components.SearchBar
import com.example.recipefinder.presentation.search.util.UiEvent
import com.example.recipefinder.util.UiText

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {event ->
            when(event) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.string.asString(context)
                    )
                }
            }

        }
    }
    val state = viewModel.state.collectAsState()
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
            },
            onCLick = {
                viewModel.onEvent(
                    SearchViewModel.SearchEvents.OnSearchClick
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