package com.example.articlesproject.main.presentation.screens.create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.articlesproject.main.data.firestore.data.CreateNewData
import com.example.articlesproject.main.data.firestore.data.DishDataFirestore
import com.example.articlesproject.main.presentation.screens.show.DishItemCompose

@Composable
fun EditDishItemCompose(
    modifier: Modifier = Modifier,
    dishData: DishDataFirestore,
    onEditClick: (dishData: DishDataFirestore) -> Unit,
    onItemClick: () -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        DishItemCompose(
            dishData = dishData,
            onClick = { onItemClick() }
        )
        EditIconButton(
            modifier = modifier
                .align(Alignment.TopEnd)
        ) { onEditClick(dishData) }
    }
}

@Composable
fun EditIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier
            .clip(CircleShape)
            .background(shape = CircleShape, color = Color.Transparent),
    ) {
        Icon(
            Icons.Filled.Edit,
            contentDescription = "EditDishItemButton",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EditDishItemPreview() {
    EditDishItemCompose(
        dishData = CreateNewData.getNewDish("Type Id"),
        onEditClick = {},
        onItemClick = {},
    )
}