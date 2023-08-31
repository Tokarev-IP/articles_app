package com.example.articlesproject.main.presentation.screens.create

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.articlesproject.main.data.data.DishData

@Composable
fun CreateDishItemCompose(
    modifier: Modifier = Modifier,
    corner: Dp,
    uri: Uri?,
    onPictureAdd: () -> Unit,
    onAddDish: (DishData) -> Unit,
) {

    val pictureUri = rememberSaveable { mutableStateOf(null) }
    val dishName = rememberSaveable { mutableStateOf("") }
    val dishPrice = rememberSaveable { mutableStateOf("") }
    val dishDescription = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.Red,
                shape = RoundedCornerShape(corner)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = modifier.height(16.dp))

        if (pictureUri.value != null)
            DishCreatePicture(
                uri = uri,
                corner = corner,
            )
        else {
            AddPictureIcon(
                onPictureAdd = { onPictureAdd() },
                corner = corner,
            )
        }
        Spacer(modifier = modifier.height(16.dp))
        TextField(
            valueText = dishName.value,
            onText = {
                dishName.value = it
            },
            corner = corner,
            keyboardType = KeyboardType.Text,
            text = "Dish name",
        )
        Spacer(modifier = modifier.height(16.dp))
        TextField(
            valueText = dishPrice.value,
            onText = {
                dishPrice.value = it
            },
            corner = corner,
            keyboardType = KeyboardType.Number,
            text = "Dish price",
        )
        Spacer(modifier = modifier.height(16.dp))
        TextField(
            valueText = dishDescription.value,
            onText = {
                dishDescription.value = it
            },
            corner = corner,
            keyboardType = KeyboardType.Text,
            text = "Dish description",
        )
        Spacer(modifier = modifier.height(24.dp))
        AddDishButton(
            onAdd = {
                onAddDish(
                    DishData(
                        pictureUri.value,
                        dishName.value,
                        dishPrice.value.toInt(),
                        dishDescription.value,
                    )
                )
                pictureUri.value = null
                dishName.value = ""
                dishPrice.value = ""
                dishDescription.value = ""
            }
        )
    }
}


@Composable
fun TextField(
    modifier: Modifier = Modifier,
    valueText: String,
    onText: (String) -> Unit,
    corner: Dp,
    keyboardType: KeyboardType,
    text: String,
) {
    OutlinedTextField(
        value = valueText,
        singleLine = true,
        shape = RoundedCornerShape(corner),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        label = { Text(text) },
        onValueChange = {
            onText(it.trim())
        })
}

@Composable
fun DishCreatePicture(
    modifier: Modifier = Modifier,
    uri: Uri?,
    corner: Dp,
) {
    AsyncImage(
        modifier = modifier
            .width(120.dp)
            .height(120.dp)
            .background(
                shape = CircleShape,
                color = Color.Unspecified,
            ),
        model = uri,
        contentDescription = "Picture of a dish",
        contentScale = ContentScale.Fit,
    )
}

@Composable
fun AddPictureIcon(
    modifier: Modifier = Modifier,
    onPictureAdd: () -> Unit,
    corner: Dp,
) {
    Column(
        modifier = modifier
            .height(120.dp)
            .background(
                color = Color.Unspecified,
                shape = CircleShape
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            Icons.Filled.Add,
            contentDescription = "AddPictureIcon",
            Modifier.clickable { onPictureAdd() }
        )
        Text(text = "Choose picture of the dish")
    }
}

@Composable
fun AddDishButton(onAdd: () -> Unit) {
    Button(onClick = { onAdd() }) {
        Icon(Icons.Filled.Add, contentDescription = "AddIcon")
        Text(text = "Add this dish")
    }
}

@Preview(showBackground = true)
@Composable
fun LogInPreview() {
    CreateDishItemCompose(
        corner = 24.dp,
        uri = null,
        onPictureAdd = {},
        onAddDish = { data: DishData -> {} })
}