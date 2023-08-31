package com.example.articlesproject.main.presentation.screens.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        CreateDishType(
            corner = corner,
            onAddType = {
                onAddType(it)
            },
        )
        Spacer(modifier = modifier.height(16.dp))
        for (i in menuDataList) {
            Text(
                text = i.type,
                modifier = modifier.width(200.dp)
            )
            Spacer(modifier = modifier.height(8.dp))
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

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        CreateTypeTextField(
            valueText = type.value,
            onText = { type.value = it },
            corner = corner,
            text = "Write dish type"
        )

        Button(
            onClick = {
                onAddType(type.value)
                type.value = ""
            }
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
) {
    OutlinedTextField(
        value = valueText,
        singleLine = true,
        shape = RoundedCornerShape(corner),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        label = { Text(text) },
        modifier = modifier.width(200.dp),
        onValueChange = {
            onText(it.trim())
        })
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