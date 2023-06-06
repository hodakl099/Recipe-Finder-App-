package com.example.recipefinder.presentation.search.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScopeInstance.weight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.recipefinder.R

@Composable
fun ProductInfoItem(
    modifier: Modifier = Modifier,
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AsyncImage(
                modifier = Modifier.weight(1f),
                model = "",
                contentDescription = "Image from api",
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground)
            )
            Column() {


            }
        }
    }
}