package com.example.articlesproject.main.presentation.screens.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.articlesproject.R
import com.example.articlesproject.main.data.data.DishData
import com.example.articlesproject.main.data.data.MenuData
import com.example.articlesproject.main.presentation.screens.show.DishItemCompose

@Composable
fun CreateDishListCompose(
    modifier: Modifier = Modifier,
    onAdd: () -> Unit,
    menuData: MenuData,
) {
    val gridState = rememberLazyGridState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = modifier.height(16.dp))
        Text(text = menuData.type, fontSize = 22.sp)
        Spacer(modifier = modifier.height(16.dp))

        when (menuData.dishesList.size) {
            0 -> {
                LazyHorizontalGridRow(
                    gridHeight = 0,
                    rows = 1,
                    menuData = menuData,
                )
            }

            1 -> {
                LazyHorizontalGridRow(
                    gridHeight = integerResource(id = R.integer.one_row_lazy_grid_height),
                    rows = 1,
                    menuData = menuData,
                )
            }

            else -> {
                if (menuData.dishesList.size >= 9)
                    LazyHorizontalGridRow(
                        gridHeight = integerResource(id = R.integer.three_rows_lazy_grid_height),
                        rows = 3,
                        menuData = menuData,
                    )
                else
                    LazyHorizontalGridRow(
                        gridHeight = integerResource(id = R.integer.two_rows_lazy_grid_height),
                        rows = 2,
                        menuData = menuData,
                    )
            }
        }
        Spacer(modifier = modifier.height(8.dp))
        Button(onClick = { onAdd() }) {
            Text(text = "Add dish")
        }
        Spacer(modifier = modifier.height(8.dp))
        Divider(
            modifier.fillMaxWidth(),
            thickness = 1.dp,
        )
    }
}

@Composable
fun LazyHorizontalGridRow(
    modifier: Modifier = Modifier,
    gridHeight: Int,
    rows: Int,
    menuData: MenuData,
) {
    val gridState = rememberLazyGridState()

    LazyHorizontalGrid(
        modifier = modifier.height(gridHeight.dp),
        rows = GridCells.Fixed(rows),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        state = gridState
    ) {
        items(menuData.dishesList.size) { index ->
            DishItemCompose(dishData = menuData.dishesList[index])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateDishListPreview() {
    CreateDishListCompose(
        onAdd = {},
        menuData = MenuData(
            "Salads", mutableListOf(
                DishData(null, "Olevie", 150, "Very tasty salad"),
                DishData(null, "Shuba", 170, "Very tasty salad"),
                DishData(null, "Vinegret", 130, "Very tasty salad"),
                DishData(null, "Craboviy", 140, "Very tasty salad"),
                DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
                DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
                DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
                DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
                DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
                DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
            )
        )
    )
}