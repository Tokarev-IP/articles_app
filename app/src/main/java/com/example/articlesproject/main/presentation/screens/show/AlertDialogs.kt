package com.example.articlesproject.main.presentation.screens.show

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DeleteAlertDialog(
    modifier: Modifier = Modifier,
    corner: Dp = 16.dp,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    title: String,
    text: String,
    confirmButtonText: String,
    dismissButtonText: String,
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(text = confirmButtonText)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = dismissButtonText)
            }
        },
        shape = RoundedCornerShape(corner),
        title = { Text(text = title, fontSize = 18.sp) },
        text = { Text(text = text, fontSize = 16.sp) },
    )
}

@Composable
fun InfoAlertDialog(
    modifier: Modifier = Modifier,
    corner: Dp = 16.dp,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    title: String,
    text: String,
    confirmButtonText: String,
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(text = confirmButtonText)
            }
        },
        shape = RoundedCornerShape(corner),
        title = { Text(text = title, fontSize = 18.sp) },
        text = { Text(text = text, fontSize = 16.sp) },
    )
}