package com.example.articlesproject.login.presentation.composable

import android.util.Log
import androidx.compose.foundation.layout.*
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
import com.example.articlesproject.login.presentation.states.UiStates

@Composable
fun NavHostCompose(
    modifier: Modifier = Modifier,
    onReceiveCode: (String) -> Unit,
    onSendCode: (String) -> Unit,
    innerPadding: PaddingValues,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "LogIn",
    authViewModel: AuthViewModel,
) {
    val state by authViewModel.getUiStateFlow().collectAsState()
    val timer by authViewModel.getTimerStateFlow().collectAsState()

    var haveGotCode by rememberSaveable { mutableStateOf(false) }
    var showProgressIndicator by rememberSaveable { mutableStateOf(false) }

    when (state) {
        is UiStates.CodeWasSent -> {
            navController.navigate("EnterCode")
            haveGotCode = true
            showProgressIndicator = false
            Log.d("MYTAG", "navigate enterCode")
        }
        is UiStates.Loading -> {
            Log.d("MYTAG", "loading")
            showProgressIndicator = true
        }
    }

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
