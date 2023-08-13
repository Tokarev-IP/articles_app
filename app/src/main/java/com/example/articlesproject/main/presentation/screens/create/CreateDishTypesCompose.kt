package com.example.articlesproject.main.presentation.screens.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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

@Composable
fun CreateDishTypesCompose(
    modifier: Modifier = Modifier,
    types: List<String>,
    corner: Dp,
    onAdd: (String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(types.size) {
                Text(text = types[it], modifier = modifier.width(200.dp))
                Spacer(modifier = modifier.height(20.dp))
            }
        }
        Spacer(modifier = modifier.height(20.dp))
        CreateDishType(
            corner = corner,
            onAdd = { onAdd(it) },
        )
    }

}

@Composable
fun CreateDishType(
    modifier: Modifier = Modifier,
    corner: Dp,
    onAdd: (String) -> Unit,
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

        Button(onClick = { onAdd(type.value) }) {
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
         types = listOf("Hot", "Cold", "Main"),
         corner = 24.dp,
         onAdd = {}
     )
}