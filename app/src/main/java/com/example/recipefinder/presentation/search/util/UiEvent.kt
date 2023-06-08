package com.example.recipefinder.presentation.search.util

import com.example.recipefinder.util.UiText

sealed class UiEvent {
    data class ShowSnackBar(val string : UiText) : UiEvent()
}
