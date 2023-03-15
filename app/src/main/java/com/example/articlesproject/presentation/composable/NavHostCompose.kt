package com.example.articlesproject.presentation.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavHostCompose(
    modifier: Modifier = Modifier,
    onReceiveCode: (String) -> Unit,
    onSendCode: (String) -> Unit,
    innerPadding: PaddingValues
) {

    val navController = rememberNavController()

    NavHost(
        modifier = modifier.padding(innerPadding),
        navController = navController,
        startDestination = "EnterCode"
    ) {
        composable("LogIn") {
            LogInScreenCompose(
                width = 320.dp,
                onReceiveCode = {
                    onReceiveCode(it)
                },
                isError = false,
            )
        }
        composable("EnterCode") {
            VerificationCodeScreenCompose(
                onSendCode = {
                    onSendCode(it) },
                width = 320.dp
            )
        }
    }
}
