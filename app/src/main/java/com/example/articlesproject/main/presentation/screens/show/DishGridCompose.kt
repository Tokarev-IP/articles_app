package com.example.articlesproject.main.presentation.screens.show

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.articlesproject.R
import com.example.articlesproject.main.data.firestore.data.CreateNewData
import com.example.articlesproject.main.data.firestore.data.MenuData
import com.example.articlesproject.main.data.firestore.data.TypeDataFirestore

@Composable
fun DishGridCompose(
    modifier: Modifier = Modifier,
    menuData: MenuData,
    onClickItem: () -> Unit,
) {
    when (menuData.dishesList.size) {
        0 -> {
            LazyHorizontalGridRow(
                gridHeight = 0,
                rows = 1,
                menuData = menuData,
                onClickItem = { onClickItem() },
            )
        }

        1, 2, 3, 4 -> {
            LazyHorizontalGridRow(
                gridHeight = integerResource(id = R.integer.one_row_lazy_grid_height),
                rows = 1,
                menuData = menuData,
                onClickItem = { onClickItem() },
            )
        }

        else -> {
            if (menuData.dishesList.size >= 14)
                LazyHorizontalGridRow(
                    gridHeight = integerResource(id = R.integer.three_rows_lazy_grid_height),
                    rows = 3,
                    menuData = menuData,
                    onClickItem = { onClickItem() },
                )
            else
                LazyHorizontalGridRow(
                    gridHeight = integerResource(id = R.integer.two_rows_lazy_grid_height),
                    rows = 2,
                    menuData = menuData,
                    onClickItem = { onClickItem() },
                )

        }
    }
}

@Composable
private fun LazyHorizontalGridRow(
    modifier: Modifier = Modifier,
    gridHeight: Int,
    rows: Int,
    menuData: MenuData,
    onClickItem: () -> Unit,
) {
    val gridState = rememberLazyGridState()

    LazyHorizontalGrid(
        modifier = modifier
            .height(gridHeight.dp)
            .fillMaxWidth(),
        rows = GridCells.Fixed(rows),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        state = gridState
    ) {
        items(menuData.dishesList.size) { index ->
            DishItemCompose(
                dishData = menuData.dishesList[index],
                onClick = { onClickItem() },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditDishGridPreview() {
    DishGridCompose(
        menuData = MenuData(
            typeData = TypeDataFirestore(
                name = "Salad",
                id = "ID",
                priority = 2,
                menuId = "id"
            ),
            dishesList = mutableListOf(
                CreateNewData.getNewDish("type Id1"),
            )
        ),
        onClickItem = { },
    )
}