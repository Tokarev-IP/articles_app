package com.example.articlesproject.main.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.articlesproject.main.MainViewModel
import com.example.articlesproject.main.presentation.screens.PicturesCompose
import com.example.articlesproject.main.presentation.screens.create.CreateItemCompose
import com.example.articlesproject.main.presentation.screens.create.CreateScreenCompose

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityCompose(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "Pic",
    onSend: () -> Unit,
    onAddPicture: () -> Unit,
) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Songs", "Artists", "Playlists")

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data: SnackbarData ->

                val buttonColor = if (true) {
                    ButtonDefaults.textButtonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.error
                    )
                } else {
                    ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.inversePrimary
                    )
                }

                Snackbar(
                    modifier = Modifier
                        .padding(24.dp),
                    action = {
                        TextButton(
                            onClick = { data.dismiss() },
                        ) { Text(data.visuals.actionLabel ?: "") }
                    }
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
        topBar = {
            TopAppBar(
                title = {
                            Text("Welcome", maxLines = 1)

                },
                navigationIcon = {
                            IconButton(onClick = {

                            }) {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowLeft,
                                    contentDescription = "Go back"
                                )
                            }
                },
            )
        },
        content = { innerPadding ->

            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
            ) {
                NavigationBar(
                    modifier = modifier
                        .padding(innerPadding)
                ) {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = { Icon(Icons.Filled.Favorite, contentDescription = item) },
                            label = { Text(item) },
                            selected = selectedItem == index,
                            onClick = { selectedItem = index }
                        )
                    }
                }
            }

//            Column(
//                modifier = modifier.fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center,
//            ) {
//                if (showProgressIndicator)
//                    CircularProgressIndicator()
//            }

            NavHost(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                navController = navController,
                startDestination = startDestination
            ) {
                composable("Pic") {
                    Log.d("MYTAG", "picture")
                    CreateScreenCompose(){}

                }
                composable("Qwe") {
                    PicturesCompose(
                        mainViewModel = mainViewModel,
                        onAddPicture = { onAddPicture() },
                        onSend = { onSend() },
                    )
                }
//                composable("EnterCode") {
//                    VerificationCodeScreenCompose(
//                        onSendCode = { code: String ->
//                            onSendCode(code)
//                        },
//                        width = 320.dp,
//                        timer = timer,
//                        onBackButton = {
//                            navController.popBackStack()
//                        },
//                        isActive = !showProgressIndicator,
//                    )
//                }
            }
        }
    )
}