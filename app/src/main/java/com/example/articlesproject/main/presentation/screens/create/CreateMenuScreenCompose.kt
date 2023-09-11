package com.example.articlesproject.main.presentation.screens.create

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.articlesproject.main.data.data.DishData
import com.example.articlesproject.main.data.data.MenuData

@Composable
fun CreateMenuScreenCompose(
    modifier: Modifier = Modifier,
    menuDataList: List<MenuData>,
    corner: Dp,
    onAddType: (String) -> Unit,
    onAdd: (numberOfType: Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
    ) {
        item {
            CreateDishTypesCompose(
                menuDataList = menuDataList,
                corner = corner,
                onAddType = {
                    onAddType(it)
                },
            )
            Spacer(modifier = modifier.height(24.dp))
            Divider(
                modifier.fillMaxWidth(),
                thickness = 1.dp,
            )
        }
        items(menuDataList.size) { index ->
            CreateDishListCompose(
                onAdd = { onAdd(index) },
                menuData = menuDataList[index],
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateScreenPreview() {
    CreateMenuScreenCompose(
        menuDataList = listOf(
            MenuData(
                "Salad", mutableListOf(
                    DishData(null, "Olevie", 150, "Very tasty salad"),
                    DishData(null, "Shuba", 170, "Very tasty salad"),
                    DishData(null, "Vinegret", 130, "Very tasty salad"),
                    DishData(null, "Craboviy", 140, "Very tasty salad"),
                    DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
                )
            )
        ),
        corner = 24.dp,
        onAddType = {},
        onAdd = {},
    )
}