package com.example.articlesproject.main.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.articlesproject.main.data.firestore.data.MenuDataFirestore
import com.example.articlesproject.main.presentation.states.UiStates

@Composable
fun CreateMenuScreen(
    modifier: Modifier = Modifier,
    uiState: UiStates,
    onAddNewMenu: () -> Unit,
    onClick: (MenuDataFirestore) -> Unit,
    menuList: List<MenuDataFirestore>,
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
                    onClick = { onAddNewMenu() },
                    modifier = modifier.padding(16.dp)
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "AddIcon")
                }
            }
        ) { padding ->
            if (uiState is UiStates.NullData) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = 48.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "There are not any data. Add some data."
                    )
                }
            }
            if (uiState is UiStates.Showing) {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = 48.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    items(menuList.size) { index ->
                        MenuItemColumnCompose(
                            name = menuList[index].name,
                            onClick = { onClick(menuList[index]) },
                        )
                        Spacer(modifier = modifier.height(24.dp))
                    }
                }
            }
        }
}

@Composable
private fun MenuItemColumnCompose(
    modifier: Modifier = Modifier,
    name: String,
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = name,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreateMenuScreenPreview() {
    CreateMenuScreen(
        onClick = {},
        onAddNewMenu = {},
        uiState = UiStates.NullData,
        menuList = listOf(
            MenuDataFirestore("Meals", "id", 1),
            MenuDataFirestore("Drinks", "id", 2)
        )
    )
}