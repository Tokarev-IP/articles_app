package com.example.articlesproject.login.presentation.composable.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import com.example.articlesproject.login.presentation.states.UiStatesLogin

@Composable
fun LogInScreenCompose(
    modifier: Modifier = Modifier,
    width: Dp,
    onReceiveCode: (number: String) -> Unit,
    onGoToCodeScreen: () -> Unit,
    haveGotCode: Boolean,
    timer: String,
    state: UiStatesLogin,
) {
    val mobileNumber = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (state) {
            is UiStatesLogin.Loading -> {
                CircularProgressIndicator()
            }

            else -> {
                MobileTextField(
                    mobileNumberText = mobileNumber.value,
                    onMobileNumber = {
                        mobileNumber.value = it
                    },
                    width = width,
                )

                Spacer(modifier = modifier.height(40.dp))

                GetCodeButton(
                    onClick = { onReceiveCode(mobileNumber.value) },
                    width = width,
                    isActive = mobileNumber.value.isNotEmpty(),
                    timer = timer,
                )

                Spacer(modifier = modifier.height(20.dp))

                AlreadyHaveCodeButton(
                    onClick = { onGoToCodeScreen() },
                    width = width,
                    haveGotCode = haveGotCode,
                )
            }
        }
    }
}

@Composable
fun MobileTextField(
    modifier: Modifier = Modifier,
    mobileNumberText: String,
    onMobileNumber: (String) -> Unit,
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
    timer: String,
    isActive: Boolean,
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
    haveGotCode: Boolean,
) {
    FilledTonalButton(
        onClick = { onClick() },
        modifier = modifier
            .width(width),
        enabled = haveGotCode,
    ) {
        Text(text = stringResource(id = R.string.already_have_code))
    }
}

@Preview(showBackground = true)
@Composable
fun LogInPreview() {
    LogInScreenCompose(
        width = 320.dp,
        onReceiveCode = {},
        onGoToCodeScreen = {},
        haveGotCode = false,
        timer = "",
        state = UiStatesLogin.Nothing,
    )
}
