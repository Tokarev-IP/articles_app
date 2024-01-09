package com.example.articlesproject.main.presentation.screens.show

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.articlesproject.R
import com.example.articlesproject.main.data.data.CreateNewData
import com.example.articlesproject.main.data.data.DishDataFirestore

@Composable
fun DishItemCompose(
    dishData: DishDataFirestore,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    topStartCorner: Dp = 16.dp,
    corner: Dp = 8.dp,
) {
    ElevatedCard(
        modifier = modifier.size(width = 240.dp, height = 240.dp),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onClick() },
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                DishPicture(
                    modifier = modifier,
                    uri = null,
                    topStartCorner = topStartCorner,
                    corner = corner,
                    height = 120.dp,
                    width = 120.dp,
                )
                DishText(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    text = stringResource(id = R.string.currency, dishData.price),
                    fontSize = 20,
                    padding = 8.dp,
                    maxLines = 1,
                )
            }
            DishText(
                modifier = modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                text = dishData.name,
                fontSize = 16,
                padding = 8.dp,
                maxLines = 3,
            )
        }
    }
}

@Composable
private fun DishText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int,
    padding: Dp,
    maxLines: Int,
) {
    Text(
        modifier = modifier.padding(top = padding, start = padding, end = padding),
        text = text,
        maxLines = maxLines,
        fontSize = fontSize.sp
    )
}

@Composable
private fun DishPicture(
    modifier: Modifier = Modifier,
    uri: Uri?,
    topStartCorner: Dp,
    corner: Dp,
    height: Dp,
    width: Dp,
) {
    AsyncImage(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = topStartCorner,
                    topEnd = corner,
                    bottomEnd = corner,
                    bottomStart = corner,
                )
            )
            .height(height)
            .width(width)
            .fillMaxWidth(),
        model = uri,
        contentDescription = "Picture of a dish",
        contentScale = ContentScale.Crop,
    )
}

@Preview(showBackground = true)
@Composable
private fun DishItemPreview() {
    DishItemCompose(
        dishData = DishDataFirestore(
            name = "Name",
            price = 10f,
            priority = 1,
            isPicture = false,
            id = "CreateNewData.getUUID()",
            typeId = "typeId",
        ),
        onClick = {}
    )
}