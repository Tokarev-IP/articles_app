package com.example.articlesproject.presentation.composable

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityCompose(
    modifier: Modifier = Modifier,
    onSendCode: (String) -> Unit,
) {
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

            NavHostCompose(
                onReceiveCode = {

                },
                onSendCode = {
                    onSendCode(it)
                },
                innerPadding = innerPadding
            )
        }
    )
}