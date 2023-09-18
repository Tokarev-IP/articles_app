package com.example.articlesproject.login.presentation.composable.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.articlesproject.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationCodeScreenCompose(
    modifier: Modifier = Modifier,
    onSendCode: (String) -> Unit,
    width: Dp,
    timer: String,
    onBackButton:() -> Unit,
    isActive: Boolean,
) {
    val code = rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
//                        Text(text = stringResource(id = R.string.code))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackButton()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
            )
        },
    ) {innerPadding ->

        Spacer(modifier = modifier.height(innerPadding.calculateTopPadding()))
        
        Column(
            modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CodeTextField(
                onCode = {
                    code.value = it
                },
                codeText = code.value,
                width = width,
                isActive = isActive,
            )

            Spacer(modifier = modifier.height(40.dp))

            SendCodeButton(
                onClick = { onSendCode(code.value) },
                width = width,
                isActive = code.value.length == 6 && isActive
            )

            Spacer(modifier = modifier.height(20.dp))

            GetCodeAgainButton(
                onClick = { /*TODO*/ },
                width = width,
                isActive = timer == "" && isActive,
                timer = timer
            )
        }
    }
}

@Composable
fun CodeTextField(
    modifier: Modifier = Modifier,
    onCode: (String) -> Unit,
    codeText: String,
    width: Dp,
    isActive: Boolean,
) {
    OutlinedTextField(
        value = codeText,
        singleLine = true,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .width(width),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text = stringResource(id = R.string.code)) },
        maxLines = 1,
//        enabled = isActive,
        onValueChange = {
            if (it.length <= 6)
                onCode(it.trim())
        })
}

@Composable
fun SendCodeButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    width: Dp,
    isActive: Boolean,
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .width(width),
        enabled = isActive,
    ) {
        Text(text = stringResource(id = R.string.log_in))
    }
}

@Composable
fun GetCodeAgainButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    width: Dp,
    isActive: Boolean,
    timer: String
) {
    FilledTonalButton(
        onClick = { onClick() },
        modifier = modifier
            .width(width),
        enabled = isActive,
    ) {
        Text(text = "$timer ${stringResource(id = R.string.get_code_again)}")
    }
}