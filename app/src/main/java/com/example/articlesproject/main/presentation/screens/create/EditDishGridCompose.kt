package com.example.articlesproject.main.presentation.screens.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.articlesproject.R
import com.example.articlesproject.main.data.firestore.data.CreateNewData
import com.example.articlesproject.main.data.firestore.data.DishDataFirestore
import com.example.articlesproject.main.data.firestore.data.MenuData
import com.example.articlesproject.main.data.firestore.data.TypeDataFirestore

@Composable
fun EditDishGridCompose(
    menuData: MenuData,
    onEditDish: (dishData: DishDataFirestore) -> Unit,
    onAddDish: () -> Unit,
    onClickItem: () -> Unit,
) {
    when (menuData.dishesList.size) {
        0, 1, 2, 3, 4, 5 -> {
            LazyHorizontalGridRow(
                gridHeight = integerResource(id = R.integer.one_row_lazy_grid_height),
                rows = 1,
                menuData = menuData,
                onEditDish = { onEditDish(it) },
                onClickItem = { onClickItem() },
                onAddDish = { onAddDish() },
            )
        }

        else -> {
            if (menuData.dishesList.size >= 15)
                LazyHorizontalGridRow(
                    gridHeight = integerResource(id = R.integer.three_rows_lazy_grid_height),
                    rows = 3,
                    menuData = menuData,
                    onEditDish = { onEditDish(it) },
                    onClickItem = { onClickItem() },
                    onAddDish = { onAddDish() },
                )
            else
                LazyHorizontalGridRow(
                    gridHeight = integerResource(id = R.integer.two_rows_lazy_grid_height),
                    rows = 2,
                    menuData = menuData,
                    onEditDish = { onEditDish(it) },
                    onClickItem = { onClickItem() },
                    onAddDish = { onAddDish() },
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
    onEditDish: (dishData: DishDataFirestore) -> Unit,
    onClickItem: () -> Unit,
    onAddDish: () -> Unit,
) {
    val gridState = rememberLazyGridState()

    LazyHorizontalGrid(
        modifier = modifier
            .height(gridHeight.dp)
            .fillMaxWidth(),
        rows = GridCells.Fixed(rows),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        state = gridState,
    ) {
        items(menuData.dishesList.size + 1) { index ->
            if (index != menuData.dishesList.size) {
                EditDishItemCompose(
                    dishData = menuData.dishesList[index],
                    onEditClick = { onEditDish(it) },
                    onItemClick = { onClickItem() },
                )
            }
            if (index == menuData.dishesList.size) {
                AddItemElevatedCard(onAdd = { onAddDish() })
            }
        }
    }
}

@Composable
private fun AddItemElevatedCard(
    modifier: Modifier = Modifier,
    onAdd: () -> Unit,
) {
    ElevatedCard(
        modifier = modifier.size(width = 240.dp, height = 240.dp),
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .clickable { onAdd() },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "AddNewDishItem",
            )
            Text(text = "Add a new dish")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditDishGridPreview() {
    EditDishGridCompose(
        menuData = MenuData(
            typeData = TypeDataFirestore(
                name = "Salad",
                id = "ID",
                priority = 2,
                menuId = "id"
            ),
            dishesList = mutableListOf(
                CreateNewData.getNewDish("type Id1"),
                CreateNewData.getNewDish("type Id2"),
                CreateNewData.getNewDish("type Id3"),
                CreateNewData.getNewDish("type Id4"),
                CreateNewData.getNewDish("type Id5"),
            )
        ),
        onEditDish = { },
        onClickItem = { },
        onAddDish = { },
    )
}