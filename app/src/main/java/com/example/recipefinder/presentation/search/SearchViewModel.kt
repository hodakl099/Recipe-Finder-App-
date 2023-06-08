package com.example.recipefinder.presentation.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.R
import com.example.recipefinder.domain.use_case.UseCases
import com.example.recipefinder.presentation.search.components.SearchState
import com.example.recipefinder.presentation.search.util.UiEvent
import com.example.recipefinder.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private var  searchJob : Job? = null



    sealed class SearchEvents {
        data class OnSearchEnter(val searchQuery : String) : SearchEvents()
        object OnSearchClick : SearchEvents()

    }

    var state = MutableStateFlow(SearchState(emptyList()))
    private set

    var searchValue by mutableStateOf("")
    private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event: SearchEvents) {
        when(event) {
            is SearchEvents.OnSearchEnter -> {
                searchValue = event.searchQuery
//                searchQuery()
            }
            is SearchEvents.OnSearchClick -> {
                state.value = state.value.copy(products = emptyList(), loading = true)
                searchQuery()
            }
        }
    }


    private fun searchQuery() {
        searchJob?.cancel()
        searchJob =  viewModelScope.launch {
            useCases.searchUseCase.invoke(searchValue).onSuccess { products ->
               state.value = state.value.copy(products = products, loading = false)
           }
                .onFailure {
                    state.value = state.value.copy(products = emptyList(), loading = false)
                    _uiEvent.send(
                        UiEvent.ShowSnackBar(
                            UiText.StringResources(R.string.something_went_wrong)
                        )
                    )
                }
        }
    }
}