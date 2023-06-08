package com.example.recipefinder.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.domain.use_case.UseCases
import com.example.recipefinder.presentation.search.components.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {


    init {
        searchQuery()
    }

    sealed class SearchEvents {

        data class OnSearchEnter(val searchQuery : String) : SearchEvents()
        object OnSearchClick : SearchEvents()

    }

    var state = MutableStateFlow(SearchState(emptyList()))
    private set

    var searchValue by mutableStateOf("")
    private set


    fun onEvent(event: SearchEvents) {
        when(event) {
            is SearchEvents.OnSearchEnter -> {
                searchValue = event.searchQuery
            }
            is SearchEvents.OnSearchClick -> {
                searchQuery()
            }
        }
    }



    private fun searchQuery() {
        viewModelScope.launch {
            useCases.searchUseCase.invoke(searchValue).onSuccess { products ->
               state.value = state.value.copy(products = products)
           }
                .onFailure { exception->
                    exception.printStackTrace()
                }
        }

    }
}