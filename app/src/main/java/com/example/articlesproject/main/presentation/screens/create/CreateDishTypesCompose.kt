package com.example.articlesproject.main.presentation.screens.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.articlesproject.R
import com.example.articlesproject.main.data.data.DishData
import com.example.articlesproject.main.data.data.MenuData

@Composable
fun CreateDishTypesCompose(
    modifier: Modifier = Modifier,
    menuDataList: List<MenuData>,
    corner: Dp,
    onAddType: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Spacer(modifier = modifier.height(24.dp))
        CreateDishType(
            corner = corner,
            onAddType = {
                onAddType(it.trim())
            },
        )
        Spacer(modifier = modifier.height(16.dp))
        var j = 0
        for (i in menuDataList) {
            j++
            Text(
                text = stringResource(R.string.type_of_dish, j, i.type),
                modifier = modifier,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun CreateDishType(
    modifier: Modifier = Modifier,
    corner: Dp,
    onAddType: (String) -> Unit,
) {
    val type = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CreateTypeTextField(
            valueText = type.value,
            onText = { type.value = it },
            corner = corner,
            text = "Write dish type",
            maximumOfLetters = integerResource(id = R.integer.maximum_of_letters_of_dish_type)
        )
        Spacer(modifier = modifier.height(12.dp))
        Button(
            modifier = modifier,
            onClick = {
                onAddType(type.value)
                type.value = ""
            },
            enabled = type.value.isNotEmpty()
        ) {
            Icon(Icons.Filled.Add, contentDescription = "AddIcon")
            Text(text = "Add type")
        }

    }
}

@Composable
fun CreateTypeTextField(
    modifier: Modifier = Modifier,
    valueText: String,
    onText: (String) -> Unit,
    corner: Dp,
    text: String,
    maximumOfLetters: Int,
) {
    OutlinedTextField(
        value = valueText,
        shape = RoundedCornerShape(corner),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        label = { Text(text) },
        onValueChange = {
            if (it.length <= maximumOfLetters)
                onText(it)
        },
        supportingText = {
            Text(
                stringResource(
                    id = R.string.maximum_of_letters,
                    valueText.length,
                    maximumOfLetters
                )
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DishTypesPreview() {
    CreateDishTypesCompose(
        menuDataList = listOf(
            MenuData(
                "Salad", mutableListOf(
                    DishData(null, "Olevie", 150, "Very tasty salad"),
                    DishData(null, "Shuba", 170, "Very tasty salad"),
                    DishData(null, "Vinegret", 130, "Very tasty salad"),
                    DishData(null, "Craboviy", 140, "Very tasty salad"),
                    DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
                )
            ),
            MenuData(
                "Salad", mutableListOf(
                    DishData(null, "Olevie", 150, "Very tasty salad"),
                    DishData(null, "Shuba", 170, "Very tasty salad"),
                    DishData(null, "Vinegret", 130, "Very tasty salad"),
                    DishData(null, "Craboviy", 140, "Very tasty salad"),
                    DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
                )
            ),
            MenuData(
                "Salad", mutableListOf(
                    DishData(null, "Olevie", 150, "Very tasty salad"),
                    DishData(null, "Shuba", 170, "Very tasty salad"),
                    DishData(null, "Vinegret", 130, "Very tasty salad"),
                    DishData(null, "Craboviy", 140, "Very tasty salad"),
                    DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
                )
            )
        ),
        corner = 24.dp,
        onAddType = {}
    )
}