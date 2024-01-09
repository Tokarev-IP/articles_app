package com.example.articlesproject.main.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.articlesproject.main.data.data.CreateNewData
import com.example.articlesproject.main.data.data.DishDataFirestore
import com.example.articlesproject.main.data.data.MenuData
import com.example.articlesproject.main.data.data.TypeDataFirestore
import com.example.articlesproject.main.presentation.screens.create.EditIconButton
import com.example.articlesproject.main.presentation.screens.show.DishGridCompose
import com.example.articlesproject.main.presentation.states.UiStates

@Composable
fun ShowDishGridScreen(
    modifier: Modifier = Modifier,
    menuDataList: List<MenuData>,
    onEditMenuGrid: (menuData: MenuData) -> Unit,
    onAddNewType: (typeData: TypeDataFirestore) -> Unit,
    uiState: UiStates,
) {
    if (uiState == UiStates.Loading)
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    else
        Scaffold(
            modifier = modifier.fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { onAddNewType(CreateNewData.getNewType()) },
                    modifier = modifier.padding(16.dp)
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "AddIcon")
                }
            }
        ) { padding ->
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(menuDataList.size) { index ->
                    ItemOfLazyColumnCompose(
                        menuData = menuDataList[index],
                        cornerShape = 16.dp,
                        backgroundColor = Color.LightGray,
                        onEditGrid = { onEditMenuGrid(menuDataList[index]) },
                        onItemClick = {
                            // TODO:
                        },
                    )
                    Spacer(modifier = modifier.height(32.dp))
                }
            }
        }
}

@Composable
private fun ItemOfLazyColumnCompose(
    modifier: Modifier = Modifier,
    menuData: MenuData,
    cornerShape: Dp,
    backgroundColor: Color,
    onEditGrid: () -> Unit,
    onItemClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(cornerShape)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = modifier.height(32.dp))
            Text(
                text = menuData.typeData.name,
                fontSize = 28.sp,
            )
            Spacer(modifier = modifier.height(32.dp))
            DishGridCompose(
                menuData = menuData,
                onClickItem = { onItemClick() }
            )
            Spacer(modifier = modifier.height(32.dp))
        }

        EditIconButton(
            modifier = modifier
                .align(Alignment.TopEnd)
        ) { onEditGrid() }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowGridScreenPreview() {
    ShowDishGridScreen(
//        menuDataList = emptyList<MenuData>(),
        menuDataList = listOf(
            MenuData(
                typeData = TypeDataFirestore(
                    name = "Salad",
                    id = "ID",
                    priority = 3,
                ),
                dishesList = listOf(
                    DishDataFirestore(
                        "Bludo 1",
                        105f,
                        1,
                        false,
                        "id",
                        "typeId",
                    ),
                    DishDataFirestore(
                        "Bludo 2",
                        105f,
                        1,
                        false,
                        "id",
                        "typeId",
                    ),
                )
            ),
        ),
        onEditMenuGrid = {},
        onAddNewType = {},
        uiState = UiStates.Showing,
    )
}