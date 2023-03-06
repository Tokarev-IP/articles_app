package com.example.articlesproject

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.articlesproject.presentation.AuthViewModel
import com.example.articlesproject.presentation.MainActivityCompose
import com.example.articlesproject.ui.theme.ArticlesProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArticlesProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StartUI()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StartUI(
        modifier: Modifier = Modifier,
        authViewModel: AuthViewModel = viewModel(),
    ) {
        val stateReg by authViewModel.stateReg.collectAsState()
        val stateLogIn by authViewModel.stateLogIn.collectAsState()
        val actionState = rememberSaveable { mutableStateOf("") }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
//                        if (actionState.value == MainActivityStates.ONE)
//                            Text("Registration", maxLines = 1)
//                        else if (actionState.value == MainActivityStates.TWO)
//                            Text("Log In", maxLines = 1)
//                        else {
//                            Text("Welcome", maxLines = 1)
//                        }
                    },
                    navigationIcon = {
//                        if (actionState.value != MainActivityStates.INITIAL)
//                            IconButton(onClick = {
//                                actionState.value = MainActivityStates.INITIAL
//                            }) {
//                                Icon(
//                                    imageVector = Icons.Filled.ArrowBack,
//                                    contentDescription = "Go back"
//                                )
//                            }
                    },
                )
            },
            content = { innerPadding ->

                Spacer(modifier = modifier.height(innerPadding.calculateTopPadding()))

                Column(
                    modifier = modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    MainActivityCompose().WriteMobileNumberCompose(
                        width = 320.dp,
                        onSendCode = {
//                            authViewModel.getVerificationCode(GetVerificationCodeUseCase(authInterface), it)
                        },
                        isError = false,
                    )

//                    if (actionState.value == MainActivityStates.ONE)
//                        RegCompose().FullRegCompose(
//                            onRegAction = { email: String, password: String ->
//                                regViewModel.regByEmail(email, password)
//                            },
//                            error = stateReg,
//                        )
//                    else if (actionState.value == MainActivityStates.TWO)
//                        LogInCompose().LogIn(
//                            onLogIn = { emailMobile: String, password: String ->
//
//                            },
//                            errors = stateLogIn,
//                        )
//                    else {
//                        WelcomeItems().MainCompose(
//                            onReg = { actionState.value = MainActivityStates.ONE },
//                            onLogIn = { actionState.value = MainActivityStates.TWO },
//                            onGoogle = {
//
//                            }
//                        )
//                    }
                }
            }
        )
    }

    fun closeSoft() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ArticlesProjectTheme() {
            StartUI()
        }
    }
}