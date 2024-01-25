package com.example.articlesproject.main.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.articlesproject.R
import com.example.articlesproject.main.data.firestore.data.DishDataFirestore
import com.example.articlesproject.main.data.firestore.data.MenuData
import com.example.articlesproject.main.data.firestore.data.TypeDataFirestore
import com.example.articlesproject.main.presentation.screens.bin.TopAppBarCompose
import com.example.articlesproject.main.presentation.screens.create.EditDishGridCompose
import com.example.articlesproject.main.presentation.screens.create.EditIconButton
import com.example.articlesproject.main.presentation.states.UiStates

@Composable
fun DishGridScreen(
    modifier: Modifier = Modifier,
    menuDataList: List<MenuData>,
    menuId: String,
    onEditMenuGrid: (menuData: MenuData) -> Unit,
    onAddNewType: (menuId: String) -> Unit,
    uiState: UiStates,
    onBackButton: () -> Unit,

    onEditDish: (dishData: DishDataFirestore) -> Unit,
    onAddDish: (typeId: String) -> Unit,
    onClickItem: () -> Unit,
    onEditType: (typeData: TypeDataFirestore) -> Unit,
) {
    when (uiState) {
        is UiStates.Loading -> {
            Column(
                modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiStates.Error -> {}

        else -> {
            Scaffold(
                modifier = modifier.fillMaxSize(),
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { onAddNewType(menuId) },
                        modifier = modifier.padding(16.dp)
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "AddIcon")
                    }
                },
                topBar = {
                    TopAppBarCompose(
                        onBackButton = { onBackButton() },
                        textId = R.string.create_a_new_dish
                    )
                }
            ) { padding ->
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(padding),
                ) {
                    if (uiState == UiStates.NullData) {
                        item {
                            Box(modifier = modifier.fillMaxSize()) {
                                Text(
                                    modifier = modifier.align(Alignment.Center),
                                    text = "There are not any types. Add some types."
                                )
                            }
                        }
                    } else
                        items(menuDataList.size) { index ->
                            ItemOfLazyColumnCompose(
                                menuData = menuDataList[index],
                                cornerShape = 16.dp,
                                backgroundColor = Color.LightGray,
                                onEditGrid = { onEditMenuGrid(menuDataList[index]) },
                                onItemClick = {
                                    // TODO:
                                },
                                onAddDish = {
                                    onAddDish(it)
                                },
                                onEditDish = {
                                    onEditDish(it)
                                },
                                onEditType = {
                                    onEditType(it)
                                },
                                onClickItem = {},
                            )
                            Spacer(modifier = modifier.height(32.dp))
                        }
                }
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
    onEditDish: (dishData: DishDataFirestore) -> Unit,
    onAddDish: (typeId: String) -> Unit,
    onClickItem: () -> Unit,
    onEditType: (typeData: TypeDataFirestore) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box {
            Text(
                modifier = modifier
                    .absolutePadding(
                        top = 48.dp,
                        right = 48.dp,
                        left = 48.dp,
                        bottom = 32.dp,
                    ),
                text = menuData.typeData.name,
                fontSize = 24.sp,
            )
            EditIconButton(
                modifier = modifier
                    .align(Alignment.TopEnd)
            ) { onEditType(menuData.typeData) }
        }
        EditDishGridCompose(
            menuData = menuData,
            onEditDish = { data: DishDataFirestore ->
                onEditDish(data)
            },
            onAddDish = { onAddDish(menuData.typeData.id) },
            onClickItem = { onClickItem() }
        )
    }
}

//    Box(
//        modifier = modifier
//            .background(
//                color = backgroundColor,
//                shape = RoundedCornerShape(cornerShape)
//            )
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            Spacer(modifier = modifier.height(32.dp))
//            Text(
//                text = menuData.typeData.name,
//                fontSize = 28.sp,
//            )
//            Spacer(modifier = modifier.height(32.dp))
//            DishGridCompose(
//                menuData = menuData,
//                onClickItem = { onItemClick() }
//            )
//            Spacer(modifier = modifier.height(32.dp))
//        }
//
//        EditIconButton(
//            modifier = modifier
//                .align(Alignment.TopEnd)
//        ) { onEditGrid() }
//    }
//}

@Preview(showBackground = true)
@Composable
private fun GridScreenPreview() {
    DishGridScreen(
        menuDataList = emptyList<MenuData>(),
//        menuDataList = listOf(
//            MenuData(
//                typeData = TypeDataFirestore(
//                    name = "Salad",
//                    id = "ID",
//                    priority = 3,
//                ),
//                dishesList = listOf(
//                    DishDataFirestore(
//                        "Bludo 1",
//                        105f,
//                        1,
//                        false,
//                        "id",
//                        "typeId",
//                    ),
//                    DishDataFirestore(
//                        "Bludo 2",
//                        105f,
//                        1,
//                        false,
//                        "id",
//                        "typeId",
//                    ),
//                )
//            ),
//        ),
        menuId = "",
        onEditMenuGrid = {},
        onAddNewType = {},
        uiState = UiStates.Showing,
        onBackButton = {},
        onClickItem = {},
        onEditType = {},
        onEditDish = {},
        onAddDish = {},
    )
}