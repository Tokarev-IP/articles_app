package com.example.articlesproject.main.presentation.screens.create

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.articlesproject.main.data.data.DishData

@Composable
fun CreateMenuScreenCompose(
    modifier: Modifier = Modifier,
    dishList: List<DishData>,
    typeList: List<String>,
    corner: Dp,
) {
    CreateDishTypesCompose(
        types = typeList,
        corner = corner,
        onAdd = {/*TODO*/ },
    )
    Spacer(modifier = modifier.height(24.dp))

    LazyColumn {
        items(dishList.size)
        {
            CreateDishListCompose(
                onAddDish = { /*TODO*/ },
                dishList = dishList,
                typeText = typeList[it]
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateScreenPreview() {
    CreateMenuScreenCompose(
        dishList = listOf(
            DishData(null, "olevie", 150, "very tasty salad")
        ),
        typeList = listOf("Main"),
        corner = 24.dp
    )
}