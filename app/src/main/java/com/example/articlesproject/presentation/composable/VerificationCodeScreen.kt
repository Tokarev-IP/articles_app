package com.example.articlesproject.presentation.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.articlesproject.ui.theme.ArticlesProjectTheme

@Composable
fun VerificationCodeScreenCompose(
    modifier: Modifier = Modifier,
    onSendCode: (String) -> Unit,
    width: Dp,
) {
    val code = rememberSaveable { mutableStateOf("") }

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
            width = width
        )

        Spacer(modifier = modifier.height(40.dp))

        SendCodeButton(
            onClick = { onSendCode(code.value) },
            width = width,
            isActive = code.value.length == 6
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeTextField(
    modifier: Modifier = Modifier,
    onCode: (String) -> Unit,
    codeText: String,
    width: Dp,
) {
    OutlinedTextField(
        value = codeText,
        singleLine = true,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .width(width),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text = "Code") },
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
    FilledTonalButton(
        onClick = { onClick() },
        modifier = modifier
            .width(width),
        enabled = isActive,
    ) {
        Text(text = "Send code")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArticlesProjectTheme() {}
}
