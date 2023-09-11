package com.example.articlesproject.main.presentation.screens.show

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.articlesproject.R
import com.example.articlesproject.main.data.data.DishData

@Composable
fun DishItemCompose(
    modifier: Modifier = Modifier,
    corner: Dp = 16.dp,
    dishData: DishData,
) {
    ElevatedCard(
        modifier = modifier.size(width = 240.dp, height = 256.dp),
    ) {
        Box {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { },
            ) {
                DishPicture(
                    modifier = modifier,
                    uri = dishData.pictureUri,
                    corner = corner,
                )
                Spacer(modifier = modifier.height(8.dp))
                DishText(
                    text = stringResource(id = R.string.currency, dishData.price),
                    fontSize = 20,
                    padding = 8,
                    maxLines = 1,
                )
                Spacer(modifier = modifier.height(4.dp))
                DishText(
                    modifier = modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    text = dishData.name,
                    fontSize = 16,
                    padding = 20,
                    maxLines = 3,
                )
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Top,
            ) {

                Button(onClick = { /*TODO*/ }, Modifier.clip(CircleShape), shape = CircleShape) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "AddDishToOrder",
                    )
                }
                Button(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Filled.Edit,
                        contentDescription = "AddDishToOrder"
                    )
                }
            }
        }
    }
}

@Composable
fun DishText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int,
    padding: Int,
    maxLines: Int,
) {
    Text(
        modifier = modifier.absolutePadding(left = padding.dp, right = 4.dp),
        text = text,
        maxLines = maxLines,
        fontSize = fontSize.sp
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
            .clip(RoundedCornerShape(bottomStart = corner, bottomEnd = corner))
            .height(120.dp)
            .fillMaxWidth(),
        model = uri,
        contentDescription = "Picture of a dish",
        contentScale = ContentScale.Crop,
    )
}

@Preview(showBackground = true)
@Composable
fun DishItemPreview() {
    DishItemCompose(
        corner = 24.dp,
        dishData = DishData(
            null,
            "Paletna di cacuhho",
            350,
            "The perfect dish for you party tonight for you and for your best friends who came back tomorrow",
        )
    )
}