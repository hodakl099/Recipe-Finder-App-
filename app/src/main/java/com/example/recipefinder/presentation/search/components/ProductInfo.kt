package com.example.recipefinder.presentation.search.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.recipefinder.R
import com.example.recipefinder.data.remote.model.Product

@Composable
fun ProductInfoItem(
    modifier: Modifier = Modifier,
    product: Product
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp),
        shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp),
        elevation = 3.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                contentDescription = "Image from api",
                painter =rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(product.image)
                        .fallback(R.drawable.ic_launcher_background)
                        .crossfade(true)
                        .build(),
                ),
                modifier = Modifier.fillMaxHeight()
                    .aspectRatio(1f)
                    .width(IntrinsicSize.Max)
                    .height(IntrinsicSize.Max)
                    .clip(RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = product.title.ifEmpty { "Unknown Recipe" },
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}
