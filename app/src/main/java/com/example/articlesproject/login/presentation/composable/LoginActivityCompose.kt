package com.example.articlesproject.login.presentation.composable

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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

    when (state) {
        is UiStatesLogin.Info -> {
            scope.launch {
                with((state as UiStatesLogin.Info).info) {
                    snackbarHostState.showSnackbar(
                        message = this,
                        actionLabel = "Result of action",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
        is UiStatesLogin.CodeWasSent -> {
            navController.navigate("EnterCode")
            haveGotCode = true
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data: SnackbarData ->
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
        content = { innerPadding ->
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
                        onGoToCodeScreen = {
                            navController.navigate("EnterCode")
                        },
                        haveGotCode = haveGotCode,
                        timer = timer,
                        state = state,
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
                        state = state,
                    )
                }
            }
        }
    )
}