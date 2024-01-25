package com.example.articlesproject.main.presentation

import android.net.Uri
import android.util.Log
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
import com.example.articlesproject.main.data.firestore.data.DishDataFirestore
import com.example.articlesproject.main.data.firestore.data.MenuData
import com.example.articlesproject.main.data.firestore.data.MenuDataFirestore
import com.example.articlesproject.main.data.firestore.data.TypeDataFirestore
import com.example.articlesproject.main.presentation.screens.CreateDataIdScreen
import com.example.articlesproject.main.presentation.screens.bin.CreateDishGridScreen
import com.example.articlesproject.main.presentation.screens.CreateDishItemScreen
import com.example.articlesproject.main.presentation.screens.CreateMenuScreen
import com.example.articlesproject.main.presentation.screens.CreateTypeScreen
import com.example.articlesproject.main.presentation.screens.DishGridScreen
import com.example.articlesproject.main.presentation.states.ScreenStates
import com.example.articlesproject.main.presentation.states.UiIntents
import com.example.articlesproject.main.presentation.viewmodel.CreateMenuViewModel
import kotlinx.coroutines.launch

@Composable
fun MainActivityCompose(
    modifier: Modifier = Modifier,
    createMenuViewModel: CreateMenuViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "CreateDataIdScreen",
    onPictureWasChosen: () -> Unit,
    onAddNewDish: (typeId: String) -> Unit,
    onAddNewType: (menuId: String) -> Unit,
    onSaveDish: (dishData: DishDataFirestore) -> Unit,
    onSaveType: (typeData: TypeDataFirestore) -> Unit,
    onCreateMenu: () -> Unit,
    onLoadIdAgain: () -> Unit,
    onAddNewMenu: () -> Unit,
) {
    val dataListState by createMenuViewModel.getDataListFlow().collectAsState()
    val state by createMenuViewModel.getUiStatesFlow().collectAsState()
    val snackBarMsg by createMenuViewModel.getSnackBarMsgFlow().collectAsState()
    val menuListState by createMenuViewModel.getMenuListFlow().collectAsState()
    val screenStates by createMenuViewModel.getScreenStateFlow().collectAsState()

    val pictureUri = remember { mutableStateOf<Uri?>(null) }
    val dishDataFirestore = remember { mutableStateOf<DishDataFirestore?>(null) }
    val typeDataFirestore = remember { mutableStateOf<TypeDataFirestore?>(null) }
    val menuDataFirestore = remember { mutableStateOf<MenuDataFirestore?>(null) }
    val dataMenu = remember { mutableStateOf<MenuData?>(null) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    when (screenStates) {
        is ScreenStates.CreateMenuScreen -> {
            navController.navigate("CreateMenuScreen")
            createMenuViewModel.setIntent(UiIntents.GetDataMenuFromFirestore)
            Log.d("DAVAI", "CreateMenuScreen")
        }

        is ScreenStates.CreateDishGridScreen -> {
            navController.navigate("CreateDishGridScreen")
            Log.d("DAVAI", "CreateDishGridScreen")
        }

        is ScreenStates.CreateDataIdScreen -> {
            navController.navigate("CreateDataIdScreen")
            Log.d("DAVAI", "CreateDataIdScreen")
        }

        is ScreenStates.CreateTypeScreen -> {
            navController.navigate("CreateTypeScreen")
            Log.d("DAVAI", "CreateTypeScreen")
        }

        is ScreenStates.CreateDishItemScreen -> {
            navController.navigate("CreateDishItemScreen")
            Log.d("DAVAI", "CreateDishItemScreen")
        }

        is ScreenStates.ShowDishGridScreen -> {
            navController.navigate("DishGridScreen")
            createMenuViewModel.setIntent(UiIntents.GetTypeDataListFromFirestore)
            createMenuViewModel.setIntent(UiIntents.GetDishDataListFromFirestore)
            Log.d("DAVAI", "DishGridScreen")
        }
    }

    scope.launch {
        snackBarMsg?.let { msg ->
            snackbarHostState.showSnackbar(
                message = msg,
                actionLabel = "Result of action",
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data: SnackbarData ->
                Snackbar(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(data.visuals.message)
                }
            }
        },
        content = { padding ->

            NavHost(
                modifier = modifier
                    .padding(padding)
                    .fillMaxSize(),
                navController = navController,
                startDestination = startDestination
            ) {
                composable("DishGridScreen") {
                    menuDataFirestore.value?.let { data: MenuDataFirestore ->
                        DishGridScreen(
                            menuDataList = dataListState,
                            menuId = data.id,
                            onEditMenuGrid = { menuData: MenuData ->
                                dataMenu.value = menuData
                                createMenuViewModel.setScreenState(ScreenStates.CreateDishGridScreen)
                            },
                            onAddNewType = { menuId: String ->
                                onAddNewType(menuId)
                            },
                            uiState = state,
                            onBackButton = {
                                navController.popBackStack()
                            },
                            onEditDish = {
                                dishDataFirestore.value = it
                                createMenuViewModel.setScreenState(ScreenStates.CreateDishItemScreen)
                            },
                            onAddDish = { typeId: String ->
                                onAddNewDish(typeId)
                            },
                            onClickItem = {},
                            onEditType = {
                                typeDataFirestore.value = it
                                createMenuViewModel.setScreenState(ScreenStates.CreateTypeScreen)
                            },
                        )
                    }
                }
                composable("CreateDishGridScreen") {
                    dataMenu.value?.let { data: MenuData ->
                        CreateDishGridScreen(
                            menuData = data,
                            onClickItem = {},
                            onEditDish = {
                                dishDataFirestore.value = it
                                createMenuViewModel.setScreenState(ScreenStates.CreateDishItemScreen)
                            },
                            onAddDish = { typeId: String ->
                                onAddNewDish(typeId)
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
                            onSaveDish = { dishData ->
                                onSaveDish(dishData)
                            },
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
                composable("CreateDataIdScreen") {
                    CreateDataIdScreen(
                        onCreateMenu = { onCreateMenu() },
                        onLoadAgain = { onLoadIdAgain() },
                        uiState = state,
                    )
                }
                composable("CreateMenuScreen") {
                    CreateMenuScreen(
                        uiState = state,
                        onAddNewMenu = { onAddNewMenu() },
                        onClick = { data: MenuDataFirestore ->
                            menuDataFirestore.value = data
                            createMenuViewModel.setScreenState(ScreenStates.ShowDishGridScreen)
                        },
                        menuList = menuListState
                    )
                }
            }
        }
    )
}