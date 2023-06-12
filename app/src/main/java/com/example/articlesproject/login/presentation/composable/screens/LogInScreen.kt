package com.example.articlesproject.login.presentation.composable.screens

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.articlesproject.R
import com.example.articlesproject.theme.ArticlesProjectTheme

@Composable
fun LogInScreenCompose(
    modifier: Modifier = Modifier,
    width: Dp,
    onReceiveCode: (number: String) -> Unit,
    isActive: Boolean,
    onGoToCodeScreen: () -> Unit,
    haveGotCode: Boolean,
    timer: String,
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
            MobileTextField(
                mobileNumberText = mobileNumber.value,
                onMobileNumber = {
                    mobileNumber.value = it
                },
                width = width,
                isActive = isActive,
            )
        }

        Spacer(modifier = modifier.height(40.dp))

        GetCodeButton(
            onClick = { onReceiveCode(mobileNumber.value) },
            width = width,
            isActive = mobileNumber.value.isNotEmpty() && isActive,
            timer = timer,
        )

        Spacer(modifier = modifier.height(20.dp))

        AlreadyHaveCodeButton(
            onClick = { onGoToCodeScreen() },
            width = width,
            isActive = isActive,
            haveGotCode = haveGotCode,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileTextField(
    modifier: Modifier = Modifier,
    mobileNumberText: String,
    onMobileNumber: (String) -> Unit,
    width: Dp,
    isActive: Boolean,
) {
    OutlinedTextField(
        value = mobileNumberText,
        singleLine = true,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .width(width),
        enabled = isActive,
        trailingIcon = {
            if (mobileNumberText.isNotEmpty())
                IconButton(onClick = { onMobileNumber("") }) {
                    Icon(Icons.Filled.Clear, contentDescription = "DeleteText")
                }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        label = { Text(text = stringResource(id = R.string.mobile_number)) },
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
    timer: String,
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .width(width),
        enabled = timer == "" && isActive,
    ) {
        Text(text = "$timer ${stringResource(id = R.string.get_code)}")
    }
}

@Composable
fun AlreadyHaveCodeButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    width: Dp,
    isActive: Boolean,
    haveGotCode: Boolean,
) {
    FilledTonalButton(
        onClick = { onClick() },
        modifier = modifier
            .width(width),
        enabled = isActive && haveGotCode,
    ) {
        Text(text = stringResource(id = R.string.already_have_code))
    }
}

@Preview(showBackground = true)
@Composable
fun LogInPreview() {
    ArticlesProjectTheme() {
        LogInScreenCompose(
            width = 320.dp,
            onReceiveCode = {},
            isActive = false,
            onGoToCodeScreen = {},
            haveGotCode = false,
            timer = "",
        )
    }
}
