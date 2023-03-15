package com.example.articlesproject.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun KeyboardAware(
    keyboardCallback: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.imePadding()) {
        keyboardCallback()
    }
}