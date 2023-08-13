package com.example.articlesproject.main.presentation.screens.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.articlesproject.main.data.data.DishData
import com.example.articlesproject.main.presentation.screens.show.DishItemCompose

@Composable
fun CreateDishListCompose(
    modifier: Modifier = Modifier,
    onAddDish: () -> Unit,
    dishList: List<DishData>,
    typeText: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = modifier.height(24.dp))
        Text(text = typeText, fontStyle = FontStyle.Italic)
        Spacer(modifier = modifier.height(24.dp))

        LazyColumn {
            items(dishList.size) {
                DishItemCompose(
                    uri = dishList[it].pictureUri,
                    corner = 24.dp,
                    dishName = dishList[it].name,
                    dishPrice = dishList[it].price,
                    dishDescription = dishList[it].description
                )
                Spacer(modifier = modifier.height(20.dp))
            }
        }
        Spacer(modifier = modifier.height(20.dp))
        CreateDishItemCompose(
            corner = 16.dp,
            uri = null,
            onPictureAdd = {},
            onAddDish = { pictureUri, dishName, dishPrice, dishDescription -> onAddDish() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreateDishListPreview() {
    CreateDishListCompose(
        onAddDish = { /*TODO*/ },
        dishList = listOf(
            DishData(null, "bulka", 150, "Myagko i vkusno"),
            DishData(null, "tort", 1500, "Cream with berries"),
        ),
        typeText = "Deserts"
    )
}