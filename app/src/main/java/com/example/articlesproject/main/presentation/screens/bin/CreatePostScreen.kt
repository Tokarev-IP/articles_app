package com.example.articlesproject.main.presentation.screens.bin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.articlesproject.R

@Composable
fun PostScreen(
    modifier: Modifier = Modifier
) {

    val title = rememberSaveable { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        PostTitle(
            onTitle = {
                title.value = it
            },
            title = title.value
        )

        Spacer(modifier = modifier.height(20.dp))

    }

}

@Composable
fun PostImage() {

//AsyncImage(model = , contentDescription = )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostTitle(
    modifier: Modifier = Modifier,
    width: Dp = 320.dp,
    onTitle: (String) -> Unit,
    title: String,
) {
    OutlinedTextField(
        value = title,
        singleLine = true,
        shape = RoundedCornerShape(20.dp),
//        modifier = modifier
//            .width(width),
        enabled = true,
        trailingIcon = {
//            if (mobileNumberText.isNotEmpty())
//                IconButton(onClick = { onMobileNumber("") }) {
//                    Icon(Icons.Filled.Clear, contentDescription = "DeleteText")
//                }
        },
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        supportingText = { Text(text = "Max 30 letters")},
        label = { Text(text = stringResource(id = R.string.header)) },
        onValueChange = {
            onTitle(it.trim())
        })
}

@Preview(showBackground = true)
@Composable
fun LogInPreview() {
    PostScreen()
}

