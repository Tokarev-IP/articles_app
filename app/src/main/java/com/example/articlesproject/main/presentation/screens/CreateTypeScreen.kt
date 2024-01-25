package com.example.articlesproject.main.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.articlesproject.R
import com.example.articlesproject.main.data.firestore.data.CreateNewData
import com.example.articlesproject.main.data.firestore.data.TypeDataFirestore
import com.example.articlesproject.main.presentation.screens.bin.TopAppBarCompose
import com.example.articlesproject.main.presentation.screens.create.TextField
import com.example.articlesproject.main.presentation.states.UiStates

@Composable
fun CreateTypeScreen(
    modifier: Modifier = Modifier,
    typeData: TypeDataFirestore,
    onBackButton: () -> Unit,
    onSaveType: (typeData: TypeDataFirestore) -> Unit,
    uiState: UiStates,
    corner: Dp = 16.dp,
) {
    val type = rememberSaveable { mutableStateOf(typeData.name) }
    val priority = rememberSaveable { mutableStateOf(typeData.priority) }

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
            topBar = {
                TopAppBarCompose(
                    onBackButton = { onBackButton() },
                    textId = R.string.edit_a_dish_type
                )
            },
        ) { innerPadding ->

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = modifier.height(64.dp))
                TextField(
                    valueText = type.value,
                    onText = { type.value = it },
                    corner = corner,
                    keyboardType = KeyboardType.Text,
                    labelText = "Type",
                    maximumOfLetters = integerResource(id = R.integer.maximum_of_letters_of_dish_type),
                    onInfo = {},
                )
                Spacer(modifier = modifier.height(16.dp))
                TextField(
                    valueText = priority.value.toString(),
                    onText = { priority.value = it.toInt() },
                    corner = corner,
                    keyboardType = KeyboardType.Number,
                    labelText = "Priority",
                    maximumOfLetters = integerResource(id = R.integer.maximum_of_letters_of_type_priority),
                    onInfo = {},
                )
                Spacer(modifier = modifier.height(32.dp))
                SaveNewTypeButton {
                    onSaveType(
                        TypeDataFirestore(
                            name = type.value,
                            id = typeData.id,
                            priority = priority.value,
                            menuId = "menu id"
                        )
                    )
                }
            }
        }
}

@Composable
private fun SaveNewTypeButton(onSave: () -> Unit) {
    Button(onClick = { onSave() }) {
        Text(text = "Save")
    }
}

@Preview(showBackground = true)
@Composable
private fun CreateDishListScreenPreview() {
    CreateTypeScreen(
        typeData = CreateNewData.getNewType("dfgdf"),
        onBackButton = {},
        onSaveType = {},
        uiState = UiStates.Showing
    )
}