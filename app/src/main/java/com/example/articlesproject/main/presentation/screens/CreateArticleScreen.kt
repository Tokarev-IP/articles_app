package com.example.articlesproject.main.presentation.screens

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.articlesproject.main.MainViewModel

@Composable
fun PicturesCompose(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    onAddPicture: () -> Unit,
    onSend: () -> Unit,
) {
    val list = mainViewModel.getImageUriFlow().collectAsState()
    val pictureAmount = rememberSaveable { mutableStateOf(1) }
    val uriList = arrayListOf<Uri>()

    Column(
        modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {

        if (list.value != null)
            AsyncImage(
                model = list.value,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                alpha = 2.0f
            )

        Button(
            modifier = modifier.height(40.dp),
            onClick = { onAddPicture() }
        ) {
            Text(text = "Add new picture")
        }

        Button(
            modifier = modifier.height(40.dp),
            onClick = { onSend() }
        ) {
            Text(text = "Send to server")
        }


    }

}

@Composable
fun ChoosePictureButton(
    modifier: Modifier = Modifier,
    onChooseFile: () -> Unit,
) {
    Button(
        modifier = modifier.height(40.dp),
        onClick = { onChooseFile() }
    ) {
        Text(text = "Choose a file")
    }
}