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
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.articlesproject.R
import com.example.articlesproject.main.data.data.DishData

@Composable
fun CreateDishItemCompose(
    modifier: Modifier = Modifier,
    corner: Dp,
    uri: Uri?,
    onPictureAdd: () -> Unit,
    onAddDish: (DishData) -> Unit,
) {
    val dishName = rememberSaveable { mutableStateOf("") }
    val dishPrice = rememberSaveable { mutableStateOf("") }
    val dishDescription = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.Unspecified,
                shape = RoundedCornerShape(corner)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = modifier.height(16.dp))

        if (uri != null)
            DishCreatePicture(uri = uri)
        else {
            AddPictureIcon(onPictureAdd = { onPictureAdd() })
        }
        Spacer(modifier = modifier.height(16.dp))
        TextField(
            valueText = dishName.value,
            onText = {
                dishName.value = it
            },
            corner = corner,
            keyboardType = KeyboardType.Text,
            labelText = "Dish name",
            maximumOfLetters = integerResource(id = R.integer.maximum_of_letters_of_dish_name)
        )
        Spacer(modifier = modifier.height(16.dp))
        TextField(
            valueText = dishPrice.value,
            onText = {
                dishPrice.value = it
            },
            corner = corner,
            keyboardType = KeyboardType.Number,
            labelText = "Dish price",
            maximumOfLetters = integerResource(id = R.integer.maximum_of_letters_of_dish_price)
        )
        Spacer(modifier = modifier.height(16.dp))
        TextField(
            valueText = dishDescription.value,
            onText = {
                dishDescription.value = it
            },
            corner = corner,
            keyboardType = KeyboardType.Text,
            labelText = "Dish description",
            maximumOfLetters = integerResource(id = R.integer.maximum_of_letters_of_dish_description)
        )
        Spacer(modifier = modifier.height(24.dp))
        AddDishButton(
            onAdd = {
                onAddDish(
                    DishData(
                        uri,
                        dishName.value,
                        dishPrice.value.toInt(),
                        dishDescription.value,
                    )
                )
//                pictureUri.value = null
                dishName.value = ""
                dishPrice.value = ""
                dishDescription.value = ""
            }
        )
        Spacer(modifier = modifier.height(24.dp))
    }
}


@Composable
fun TextField(
    modifier: Modifier = Modifier,
    valueText: String,
    onText: (String) -> Unit,
    corner: Dp,
    keyboardType: KeyboardType,
    labelText: String,
    maximumOfLetters: Int,
) {
    OutlinedTextField(
        value = valueText,
        shape = RoundedCornerShape(corner),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        label = { Text(labelText) },
        onValueChange = {
            if (valueText.length <= maximumOfLetters)
                onText(it)
        },
        supportingText = {
            Text(
                text = stringResource(
                    id = R.string.maximum_of_letters,
                    valueText.length,
                    maximumOfLetters
                )
            )
        }
    )
}

@Composable
fun DishCreatePicture(
    modifier: Modifier = Modifier,
    uri: Uri?,
) {
    AsyncImage(
        modifier = modifier
            .width(160.dp)
            .height(160.dp)
            .clip(CircleShape),
        model = uri,
        contentDescription = "Picture of a dish",
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun AddPictureIcon(
    modifier: Modifier = Modifier,
    onPictureAdd: () -> Unit,
) {
    Column(
        modifier = modifier
            .width(320.dp)
            .clip(CircleShape)
            .clickable { onPictureAdd() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            Icons.Filled.Add,
            contentDescription = "AddPictureIcon",
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