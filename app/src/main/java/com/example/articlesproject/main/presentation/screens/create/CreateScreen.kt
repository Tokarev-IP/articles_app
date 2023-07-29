package com.example.articlesproject.main.presentation.screens.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CreateScreenCompose(
    modifier: Modifier = Modifier,
    onPictureAdd: () -> Unit,
) {

    val dishCount = rememberSaveable { mutableStateOf(0) }
    val rememberScroll = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
//            .verticalScroll(rememberScroll),
    ) {
        LazyColumn {
            items(dishCount.value) {
                CreateItemCompose(
                    corner = 16.dp,
                    uri = null,
                    onPictureAdd = { onPictureAdd() }
                )
            }
        }

        AddDishButton {
            dishCount.value++
        }

    }


}

@Composable
fun AddDishButton(onAddDish: () -> Unit) {
    Button(onClick = { onAddDish() }) {
        Icon(Icons.Filled.Add, contentDescription = "AddDishIcon")
        Text(text = "Add new dish")
    }
}

@Preview(showBackground = true)
@Composable
fun CreateScreenPreview() {
    CreateScreenCompose(onPictureAdd = {})
}