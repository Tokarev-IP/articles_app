package com.example.articlesproject.presentation.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.articlesproject.ui.theme.ArticlesProjectTheme

@Composable
fun LogInScreenCompose(
    modifier: Modifier = Modifier,
    width: Dp,
    onReceiveCode: (number: String) -> Unit,
    isError: Boolean,
) {
    val mobileNumber = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "+")
            Text(text = "555")
            MobileTextField(
                isError = isError,
                mobileNumberText = mobileNumber.value,
                onMobileNumber = {
                    mobileNumber.value = it
                },
                errorText = "",
                width = width
            )
        }

        Spacer(modifier = modifier.height(40.dp))

        GetCodeButton(
            onClick = { onReceiveCode(mobileNumber.value) },
            width = width,
            isActive = mobileNumber.value.isNotEmpty()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileTextField(
    modifier: Modifier = Modifier,
    isError: Boolean,
    mobileNumberText: String,
    onMobileNumber: (String) -> Unit,
    errorText: String,
    width: Dp,
) {
    OutlinedTextField(
        value = mobileNumberText,
        singleLine = true,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .width(width),
        trailingIcon = {
            if (mobileNumberText.isNotEmpty())
                IconButton(onClick = { onMobileNumber("") }) {
                    Icon(Icons.Filled.Clear, contentDescription = "DeleteText")
                }
        },
        supportingText = {
            if (isError) Text(text = errorText, color = Color.Red)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        label = { Text(text = "Mobile number") },
        onValueChange = {
            onMobileNumber(it.trim())
        })
}

@Composable
fun GetCodeButton(
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
        Text(text = "Get code")
    }
}

@Preview(showBackground = true)
@Composable
fun LogInPreview() {
    ArticlesProjectTheme() {
        LogInScreenCompose(
            width = 320.dp,
            onReceiveCode = {},
            isError = false
        )
    }
}
