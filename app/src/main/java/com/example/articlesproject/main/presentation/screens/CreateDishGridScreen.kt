package com.example.articlesproject.main.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.articlesproject.R
import com.example.articlesproject.main.data.data.CreateNewData
import com.example.articlesproject.main.data.data.DishDataFirestore
import com.example.articlesproject.main.data.data.MenuData
import com.example.articlesproject.main.data.data.TypeDataFirestore
import com.example.articlesproject.main.presentation.screens.create.EditDishGridCompose
import com.example.articlesproject.main.presentation.screens.create.EditIconButton

@Composable
fun CreateDishGridScreen(
    modifier: Modifier = Modifier,
    corner: Dp = 16.dp,
    menuData: MenuData,
    onBackButton: () -> Unit,
    onEditDish: (dishData: DishDataFirestore) -> Unit,
    onAddDish: (dishData: DishDataFirestore) -> Unit,
    onClickItem: () -> Unit,
    onEditType: (typeData: TypeDataFirestore) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBarCompose(
                onBackButton = { onBackButton() },
                textId = R.string.edit_a_dish_list
            )
        },
    ) { innerPadding ->

        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
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
                    onEditDish = {
                        onEditDish(it)
                    },
                    onAddDish = { onAddDish(CreateNewData.getNewDish(menuData.typeData.id)) },
                    onClickItem = { onClickItem() }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarCompose(
    onBackButton: () -> Unit,
    textId: Int,
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = textId))
        },
        navigationIcon = {
            IconButton(onClick = {
                onBackButton()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Go back"
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun CreateDishListScreenPreview() {
    CreateDishGridScreen(
        menuData = MenuData(
            typeData = TypeDataFirestore(
                name = "Salad",
                id = "ID",
                priority = 3,
            ),
            dishesList = mutableListOf()
        ),
        onBackButton = {},
        onClickItem = {},
        onAddDish = { },
        onEditDish = {},
        onEditType = {},
    )
}