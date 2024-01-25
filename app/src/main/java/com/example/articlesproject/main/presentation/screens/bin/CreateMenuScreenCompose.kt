package com.example.articlesproject.main.presentation.screens.bin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.articlesproject.main.data.firestore.data.MenuData

@Composable
fun CreateMenuScreenCompose(
    modifier: Modifier = Modifier,
    menuDataList: List<MenuData>,
    corner: Dp,
    onAddType: (String) -> Unit,
    onAdd: (numberOfType: Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
    ) {
        items(menuDataList.size) { index ->
//            EditDishListCompose(
//                menuData = menuDataList[index],
//                corner = 16.dp,
//            )
        }

        item {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = modifier.height(32.dp))
                Button(
                    onClick = { /*TODO*/ },
                ) {
                    Text(text = "Add a new type of a dish")
                }
            }
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun CreateScreenPreview() {
//    CreateMenuScreenCompose(
//        menuDataList = listOf(
//            MenuData(
//                "Salad", mutableListOf(
//                    DishData(null, "Olevie", 150, "Very tasty salad"),
//                    DishData(null, "Shuba", 170, "Very tasty salad"),
//                    DishData(null, "Vinegret", 130, "Very tasty salad"),
//                    DishData(null, "Craboviy", 140, "Very tasty salad"),
//                    DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
//                )
//            )
//        ),
//        corner = 24.dp,
//        onAddType = {},
//        onAdd = {},
//    )
//}