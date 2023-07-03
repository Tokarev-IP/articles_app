package com.example.articlesproject.main.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.articlesproject.main.MainViewModel

@Composable
fun TextCompose(
    modifier: Modifier = Modifier,
    onChooseFile: () -> Unit,
    mainViewModel: MainViewModel,
    onSendPhoto:() -> Unit,
) {
    val list = mainViewModel.getImageUriFlow().collectAsState()

    Column(
        modifier.fillMaxSize(),
    ) {

        ChoosePictureButton(
            onChooseFile = { onChooseFile() }
        )

        AsyncImage(
            model = list.value,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            alpha = 2.0f
        )

        Button(
            modifier = modifier.height(40.dp),
            onClick = { onSendPhoto() }
        ) {
            Text(text = "Send photo to server")
        }

    }

//        val bitmap = BitmapFactory.decodeStream(FileInputStream(File("content://com.android.providers.media.documents/document/image%3A267964")))

//        Image(bitmap = bitmap.asImageBitmap(), contentDescription = null)

//        Image(painter = rememberAsyncImagePainter(
//                Uri.parse(
//                    "content://com.android.providers.media.documents/document/image%3A267964")
//            ),
//            contentDescription = null
//        )

//        AsyncImage(
//            model = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiQyCrAWdIb8-moiYuP7EdpznRLOLaKoZWJ04MLzMi1wkJrMfLKQshwXhB_ODNz3T6_aoOwQ0YccVpSbLO2K9qkpx-HTklvNm3ZR_spOINLr861_PgDXDnh6LgpptIyzR5Nv-UjlQ-5FyeLpHwOCb4NjZ8darLIomTVjHM2VvDv7YZdzO-FS6zMKEhlCQ/s1600/Android-JetpackCompose1.2-Social.png",
//            contentDescription = null,
//        )
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