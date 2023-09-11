package com.example.articlesproject.login.presentation.composable

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.articlesproject.login.presentation.AuthViewModel
import com.example.articlesproject.login.presentation.composable.screens.LogInScreenCompose
import com.example.articlesproject.login.presentation.composable.screens.VerificationCodeScreenCompose
import com.example.articlesproject.login.presentation.states.UiStatesLogin
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginActivityCompose(
    modifier: Modifier = Modifier,
    onSendCode: (String) -> Unit,
    onReceiveCode: (String) -> Unit,
    authViewModel: AuthViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "LogIn",
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val state by authViewModel.getUiStateFlow().collectAsState()
    val timer by authViewModel.getTimerStateFlow().collectAsState()

    var haveGotCode by rememberSaveable { mutableStateOf(false) }
    var showProgressIndicator by rememberSaveable { mutableStateOf(false) }

    when (state) {
        is UiStatesLogin.Info -> {
            scope.launch {
                with((state as UiStatesLogin.Info).info) {
                    Log.d("MYTAG", "snack bar")
                    snackbarHostState.showSnackbar(
                        message = this,
                        actionLabel = "Result of action",
                        duration = SnackbarDuration.Short
                    )
                }
            }
            showProgressIndicator = false
        }
        is UiStatesLogin.CodeWasSent -> {
            navController.navigate("EnterCode")
            haveGotCode = true
            showProgressIndicator = false
            Log.d("MYTAG", "navigate enterCode")
        }
        is UiStatesLogin.Loading -> {
            Log.d("MYTAG", "loading")
            showProgressIndicator = true
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data: SnackbarData ->

//                val buttonColor = if (true) {
//                    ButtonDefaults.textButtonColors(
//                        containerColor = MaterialTheme.colorScheme.errorContainer,
//                        contentColor = MaterialTheme.colorScheme.error
//                    )
//                } else {
//                    ButtonDefaults.textButtonColors(
//                        contentColor = MaterialTheme.colorScheme.inversePrimary
//                    )
//                }

                Snackbar(
                    modifier = Modifier
//                        .border(2.dp, MaterialTheme.colorScheme.secondary)
                        .padding(24.dp),
//                    action = {
//                        TextButton(
//                            border = BorderStroke(1.dp, Color.Blue),
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
//                        if (actionState.value == MainActivityStates.ONE)
//                            Text("Registration", maxLines = 1)
//                        else if (actionState.value == MainActivityStates.TWO)
//                            Text("Log In", maxLines = 1)
//                        else {
//                            Text("Welcome", maxLines = 1)
//                        }
//                },
//                navigationIcon = {
//                            IconButton(onClick = {
//
//                            }) {
//                                Icon(
//                                    imageVector = Icons.Filled.ArrowBack,
//                                    contentDescription = "Go back"
//                                )
//                            }
//                },
//            )
//        },
        content = { innerPadding ->

            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                if (showProgressIndicator)
                    CircularProgressIndicator()
            }

            NavHost(
                modifier = modifier.padding(innerPadding),
                navController = navController,
                startDestination = startDestination
            ) {
                composable("LogIn") {
                    LogInScreenCompose(
                        width = 320.dp,
                        onReceiveCode = { phoneNumber: String ->
                            onReceiveCode(phoneNumber)
                        },
                        isActive = !showProgressIndicator,
                        onGoToCodeScreen = {
                            navController.navigate("EnterCode")
                        },
                        haveGotCode = haveGotCode,
                        timer = timer,
                    )
                }
                composable("EnterCode") {
                    VerificationCodeScreenCompose(
                        onSendCode = { code: String ->
                            onSendCode(code)
                        },
                        width = 320.dp,
                        timer = timer,
                        onBackButton = {
                            navController.popBackStack()
                        },
                        isActive = !showProgressIndicator,
                    )
                }
            }
        }
    )
}