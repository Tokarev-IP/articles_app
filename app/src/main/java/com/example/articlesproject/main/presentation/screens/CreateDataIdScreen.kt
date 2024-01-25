package com.example.articlesproject.main.presentation.screens

import android.media.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.articlesproject.R
import com.example.articlesproject.main.presentation.states.UiStates

@Composable
fun CreateDataIdScreen(
    modifier: Modifier = Modifier,
    onCreateMenu: () -> Unit,
    onLoadAgain: () -> Unit,
    uiState: UiStates,
) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState) {
            is UiStates.Loading -> {
                CircularProgressIndicator()
            }

            is UiStates.Error -> {
                Text(
                    text = stringResource(id = R.string.there_is_any_error),
                    modifier = modifier
                        .absolutePadding(60.dp, 60.dp, 60.dp, 60.dp)
                )
                Button(
                    onClick = { onLoadAgain() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = "Go back"
                    )
                }
            }

            is UiStates.NullData -> {
                Text(
                    text = stringResource(id = R.string.there_is_no_any_menu),
                    modifier = modifier
                        .absolutePadding(60.dp, 60.dp, 60.dp, 60.dp)
                )
                Button(
                    onClick = { onCreateMenu() }
                ) {
                    Text(text = "Create a menu")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateDataIdPreview() {
    CreateDataIdScreen(
        onCreateMenu = {},
        onLoadAgain = {},
        uiState = UiStates.Error,
    )
}