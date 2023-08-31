package com.example.articlesproject.main.presentation.screens.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.articlesproject.main.data.data.DishData
import com.example.articlesproject.main.data.data.MenuData
import com.example.articlesproject.main.presentation.screens.show.DishItemCompose

@Composable
fun CreateDishListCompose(
    modifier: Modifier = Modifier,
    onAddDish: (dishData: DishData) -> Unit,
    onAdd: () -> Unit,
    menuData: MenuData,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = modifier.height(20.dp))
        Text(text = menuData.type, fontSize = TextUnit(20f, TextUnitType.Sp))
        Spacer(modifier = modifier.height(20.dp))
        for (i in menuData.dishesList) {
            DishItemCompose(
                uri = i.pictureUri,
                corner = 24.dp,
                dishName = i.name,
                dishPrice = i.price,
                dishDescription = i.description
            )
            Spacer(modifier = modifier.height(16.dp))
        }
        Spacer(modifier = modifier.height(16.dp))
        Button(onClick = { onAdd() }) {
            Text(text = "Add dish")
        }
//        CreateDishItemCompose(
//            corner = 16.dp,
//            uri = null,
//            onPictureAdd = {},
//            onAddDish = { dishData: DishData ->
//                onAddDish(dishData)
//            }
//        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreateDishListPreview() {
    CreateDishListCompose(
        onAddDish = { /*TODO*/ },
        onAdd = {},
        menuData = MenuData(
            "Salads", mutableListOf(
                DishData(null, "Olevie", 150, "Very tasty salad"),
                DishData(null, "Shuba", 170, "Very tasty salad"),
                DishData(null, "Vinegret", 130, "Very tasty salad"),
                DishData(null, "Craboviy", 140, "Very tasty salad"),
//                DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
//                DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
//                DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
//                DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
//                DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
//                DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
            )
        )
    )
}