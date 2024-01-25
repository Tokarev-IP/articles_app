package com.example.articlesproject.main.presentation.screens.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.articlesproject.R

@Composable
fun CreateDishTextFieldsCompose(
    modifier: Modifier = Modifier,
    corner: Dp,
    dishName: String,
    dishPrice: Double,
    dishPriority: Int,
    onDishName: (name: String) -> Unit,
    onDishPrice: (price: Double) -> Unit,
    onDishPriority: (priority: Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            valueText = dishName,
            onText = {
                onDishName(it)
            },
            corner = corner,
            keyboardType = KeyboardType.Text,
            labelText = "Dish name",
            maximumOfLetters = integerResource(id = R.integer.maximum_of_letters_of_dish_name),
            onInfo = {}
        )
        Spacer(modifier = modifier.height(32.dp))
        TextField(
            valueText = dishPrice.toString(),
            onText = {
                onDishPrice(it.toDouble())
            },
            corner = corner,
            keyboardType = KeyboardType.Number,
            labelText = "Dish price",
            maximumOfLetters = integerResource(id = R.integer.maximum_of_letters_of_dish_price),
            onInfo = {}
        )
        Spacer(modifier = modifier.height(32.dp))
        TextField(
            valueText = dishPriority.toString(),
            onText = {
                onDishPriority(it.toInt())
            },
            corner = corner,
            keyboardType = KeyboardType.Number,
            labelText = "Dish priority",
            maximumOfLetters = integerResource(id = R.integer.maximum_of_letters_of_dish_priority),
            onInfo = {}
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
    labelText: String,
    maximumOfLetters: Int,
    onInfo: () -> Unit,
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
        },
        trailingIcon = {
            if (valueText.isNotEmpty())
                IconButton(onClick = { onText("") }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "ClearTextField"
                    )
                }
            else
                IconButton(onClick = { onInfo() }) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "InfoTextField"
                    )
                }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun LogInPreview() {
    CreateDishTextFieldsCompose(
        corner = 24.dp,
        dishName = "Name",
        dishPrice = 150.32,
        dishPriority = 1,
        onDishName = {},
        onDishPrice = {},
        onDishPriority = {}
    )
}