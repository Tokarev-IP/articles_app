package com.example.articlesproject.main.presentation.screens.show

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun DishItemCompose(
    modifier: Modifier = Modifier,
    uri: Uri?,
    corner: Dp,
    dishName: String,
    dishPrice: Int,
    dishDescription: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.Green,
                shape = RoundedCornerShape(corner)
            ),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier
                .width(220.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
        ) {
            Spacer(modifier = modifier.height(16.dp))
            DishText(text = dishName)
            Spacer(modifier = modifier.height(16.dp))
            DishText(text = dishPrice.toString())
            Spacer(modifier = modifier.height(16.dp))
            DishText(text = dishDescription)
            Spacer(modifier = modifier.height(16.dp))
        }
        DishPicture(
            uri = uri,
            corner = corner
        )
    }
}

@Composable
fun DishText(
    text: String,
) {
    Text(
        text = text,
        maxLines = 1,
    )
}

@Composable
fun DishPicture(
    modifier: Modifier = Modifier,
    uri: Uri?,
    corner: Dp,
) {
    AsyncImage(
        modifier = modifier
            .width(100.dp)
            .height(100.dp)
            .background(
                shape = CircleShape,
                color = Color.Yellow,
            ),
        model = uri,
        contentDescription = "Picture of a dish",
        contentScale = ContentScale.Fit,
    )
}

@Preview(showBackground = true)
@Composable
fun DishItemPreview() {
    DishItemCompose(
        uri = null,
        corner = 24.dp,
        dishName = "Paletna di cacuhho",
        dishPrice = 350,
        dishDescription = "The perfect dish for you party tonight for you and for your best friends who came back tomorrow",
    )
}