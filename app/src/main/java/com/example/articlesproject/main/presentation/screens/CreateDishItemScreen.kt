package com.example.articlesproject.main.presentation.screens

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.articlesproject.R
import com.example.articlesproject.main.data.firestore.data.CreateNewData
import com.example.articlesproject.main.data.firestore.data.DishDataFirestore
import com.example.articlesproject.main.presentation.screens.bin.TopAppBarCompose
import com.example.articlesproject.main.presentation.screens.create.CreateDishTextFieldsCompose
import com.example.articlesproject.main.presentation.states.UiStates

@Composable
fun CreateDishItemScreen(
    modifier: Modifier = Modifier,
    dishData: DishDataFirestore,
    uri: Uri?,
    onBackButton: () -> Unit,
    onPictureAdd: () -> Unit,
    onSaveDish: (dishData: DishDataFirestore) -> Unit,
    uiState: UiStates,
) {
    val isPicture = rememberSaveable { mutableStateOf(dishData.isPicture) }
    val dishUri = rememberSaveable { mutableStateOf(uri) }
    val dishName = rememberSaveable { mutableStateOf(dishData.name) }
    val dishPrice = rememberSaveable { mutableDoubleStateOf(dishData.price) }
    val dishPriority = rememberSaveable { mutableIntStateOf(dishData.priority) }

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
                    textId = R.string.create_a_new_dish
                )
            },
        ) { innerPadding ->

            Column(
                modifier = modifier
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = modifier.height(48.dp))
                if (uri != null)
                    DishPictureCompose(
                        uri = dishUri.value,
                        onDeletePicture = { isPicture.value = false }
                    )
                else {
                    AddPictureIcon(onPictureAdd = { onPictureAdd() })
                }
                Spacer(modifier = modifier.height(32.dp))
                CreateDishTextFieldsCompose(
                    corner = 24.dp,
                    dishName = dishName.value,
                    dishPrice = dishPrice.doubleValue,
                    dishPriority = dishPriority.intValue,
                    onDishName = { dishName.value = it },
                    onDishPrice = { dishPrice.doubleValue = it },
                    onDishPriority = { dishPriority.intValue = it },
                )
                Spacer(modifier = modifier.height(32.dp))
                SaveDishButton(
                    onSave = {
                        onSaveDish(
                            DishDataFirestore(
                                name = dishName.value,
                                price = dishPrice.doubleValue,
                                priority = dishPriority.intValue,
                                isPicture = isPicture.value,
                                id = dishData.id,
                                typeId = dishData.typeId,
                            ),
                        )
                    }
                )
                Spacer(modifier = modifier.height(24.dp))
            }
        }
}

@Composable
private fun DishPictureCompose(
    modifier: Modifier = Modifier,
    uri: Uri?,
    onDeletePicture: () -> Unit,
) {
    Box(
        modifier = modifier.absolutePadding(
            left = 8.dp,
            right = 8.dp,
            top = 8.dp,
            bottom = 8.dp,
        )
    ) {
        DishPicture(uri = uri)

        IconButton(
            onClick = { onDeletePicture() },
            modifier = modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                Icons.Filled.Clear,
                contentDescription = "ClearPicture",
            )
        }
    }
}

@Composable
private fun DishPicture(
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
private fun AddPictureIcon(
    modifier: Modifier = Modifier,
    onPictureAdd: () -> Unit,
    corner: Dp = 16.dp,
) {
    Column(
        modifier = modifier
            .width(320.dp)
            .clip(RoundedCornerShape(corner))
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
private fun SaveDishButton(onSave: () -> Unit) {
    Button(onClick = { onSave() }) {
        Text(text = "Save this dish")
    }
}

@Preview(showBackground = true)
@Composable
fun CreateDishListPreview() {
    CreateDishItemScreen(
        uri = "URI".toUri(),
        dishData = CreateNewData.getNewDish("ip"),
        onBackButton = { /*TODO*/ },
        onPictureAdd = {/*TODO*/ },
        onSaveDish = {},
        uiState = UiStates.Showing,
    )
}