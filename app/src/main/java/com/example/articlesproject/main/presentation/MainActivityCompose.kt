package com.example.articlesproject.main.presentation

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.articlesproject.main.data.data.DishDataFirestore
import com.example.articlesproject.main.data.data.MenuData
import com.example.articlesproject.main.data.data.TypeDataFirestore
import com.example.articlesproject.main.presentation.screens.CreateDishGridScreen
import com.example.articlesproject.main.presentation.screens.CreateDishItemScreen
import com.example.articlesproject.main.presentation.screens.CreateTypeScreen
import com.example.articlesproject.main.presentation.screens.ShowDishGridScreen
import com.example.articlesproject.main.presentation.states.UiStates
import kotlinx.coroutines.launch

@Composable
fun MainActivityCompose(
    modifier: Modifier = Modifier,
    createMenuViewModel: CreateMenuViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "ShowDishGridScreen",
    onPictureWasChosen: () -> Unit,
    onSaveDish: (dishData: DishDataFirestore) -> Unit,
    onSaveType: (typeData: TypeDataFirestore) -> Unit,
) {
    val menuDataListState by createMenuViewModel.getMenuDataListFlow().collectAsState()
    val state by createMenuViewModel.getUiStatesFlow().collectAsState()

    val dishDataFirestore = remember { mutableStateOf<DishDataFirestore?>(null) }
    val typeDataFirestore = remember { mutableStateOf<TypeDataFirestore?>(null) }
    val menuData = remember { mutableStateOf<MenuData?>(null) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    when (state) {
        is UiStates.Info -> {
            scope.launch {
                with(state as UiStates.Info) {
                    snackbarHostState.showSnackbar(
                        message = this.msg,
                        actionLabel = "Result of action",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data: SnackbarData ->
                Snackbar(
                    modifier = Modifier
                        .padding(24.dp),
//                    action = {
//                        TextButton(
//                            onClick = { data.dismiss() },
//                        ) { Text(data.visuals.actionLabel ?: "") }
//                    }
                ) {
                    Text(data.visuals.message)
                }
            }
        },
//        floatingActionButton = {
//            val clickCount = remember { mutableStateOf(0) }
//            ExtendedFloatingActionButton(
//                onClick = {
//                    // show snackbar as a suspend function
//                    scope.launch {
//                        snackbarHostState.showSnackbar(
//                            message = "Snackbar # ${++clickCount.value}",
//                            actionLabel = "Action",
//                            withDismissAction = true,
//                            duration = SnackbarDuration.Indefinite
//                        )
//                    }
//                }
//            ) { Text("Show snackbar") }
//        },
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text("Welcome", maxLines = 1)
//
//                },
//                navigationIcon = {
//                    IconButton(onClick = {
//
//                    }) {
//                        Icon(
//                            imageVector = Icons.Filled.KeyboardArrowLeft,
//                            contentDescription = "Go back"
//                        )
//                    }
//                },
//            )
//        },
        content = { padding ->

            NavHost(
                modifier = modifier
                    .padding(padding)
                    .fillMaxSize(),
                navController = navController,
                startDestination = startDestination
            ) {
                composable("ShowDishGridScreen") {
                    ShowDishGridScreen(
                        menuDataList = menuDataListState,
                        onEditMenuGrid = {
                            menuData.value = it
                            navController.navigate("CreateDishGridScreen")
                        },
                        onAddNewType = {
                            onSaveType(it)
                        },
                        uiState = state,
                    )
                }
                composable("CreateDishGridScreen") {
                    menuData.value?.let { data ->
                        CreateDishGridScreen(
                            menuData = data,
                            onClickItem = {},
                            onEditDish = {
                                dishDataFirestore.value = it
                            },
                            onAddDish = { typeId ->
                                onSaveDish(typeId)
                            },
                            onBackButton = { navController.popBackStack() },
                            onEditType = {

                            }
                        )
                    }
                }
                composable("CreateDishItemScreen") {
                    dishDataFirestore.value?.let { data ->
                        val uri: Uri? = if (data.isPicture) data.id.toUri() else null

                        CreateDishItemScreen(
                            dishData = data,
                            uri = uri,
                            onBackButton = { navController.popBackStack() },
                            onSaveDish = { onSaveDish(it) },
                            onPictureAdd = { onPictureWasChosen() },
                            uiState = state,
                        )
                    }
                }
                composable("CreateTypeScreen") {
                    typeDataFirestore.value?.let { data ->
                        CreateTypeScreen(
                            typeData = data,
                            onBackButton = { navController.popBackStack() },
                            onSaveType = {
                                onSaveType(it)
                            },
                            uiState = state,
                        )
                    }
                }
            }
        }
    )
}