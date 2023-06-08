package com.example.recipefinder.util

import android.content.Context

sealed class UiText {
    data class StringResources(val stringRes : Int) : UiText()

    fun asString(context : Context) : String {
        return when(this) {
            is StringResources -> context.getString(stringRes)
        }
    }
}