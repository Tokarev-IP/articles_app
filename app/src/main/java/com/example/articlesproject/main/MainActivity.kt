package com.example.articlesproject.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import coil.decode.ImageSource
import com.example.articlesproject.login.presentation.AuthViewModel
import com.example.articlesproject.main.data.ActivityResultUri
import com.example.articlesproject.theme.ArticlesProjectTheme
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.security.cert.Extension
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private val storage = Firebase.storage
    private var storageRef = storage.reference
    private var imagesRef: StorageReference = storageRef.child("gs://articles-app-8b12b.appspot.com/photo.jpg")
//    private val fileName = "space.jpg"
//    var spaceRef = imagesRef.child(fileName)

    lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArticlesProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StartUi()
                }
            }
        }

//        val pickPhoto = registerForActivityResult(object :
//            ActivityResultContracts.PickVisualMedia() {}, ActivityResultUri<Uri?>(), )

        pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
                Log.d("PhotoPicker", "Selected URI: ${uri.path}")
                mainViewModel.setImageUri(uri)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

//        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
    }

    @Composable
    fun StartUi(){
        val mainViewModel = hiltViewModel<MainViewModel>()

        TextCompose(
            onChooseFile = {
                pickUp()
            },
            mainViewModel = mainViewModel,
            onSendPhoto = {
                    storage.getReference("photos/image/").child("/path/").putFile(mainViewModel.uri)
                        .addOnSuccessListener {
                            Log.d("MYTAG_LOAD", "loadSuccess")
                        }
                        .addOnCompleteListener {
                            Log.d("MYTAG_LOAD", "loadSuccess")
                        }
                        .addOnFailureListener {
                            Log.d("MYTAG_LOAD", "loadFailed + $it")
                        }

//                    storageRef.putFile(mainViewModel.uri)
//                        .addOnSuccessListener {
//                            Log.d("MYTAG_LOAD", "loadSuccess")
//                        }
//                        .addOnFailureListener {
//                            Log.d("MYTAG_LOAD", "loadFailed + $it")
//                        }
            }
        )
    }

    fun setUrl(){
        // Create a storage reference from our app
        val storageRef = storage.reference

// Create a reference to "mountains.jpg"
        val mountainsRef = storageRef.child("mountains.jpg")

// Create a reference to 'images/mountains.jpg'
        val mountainImagesRef = storageRef.child("images/mountains.jpg")

// While the file names are the same, the references point to different files
        mountainsRef.name == mountainImagesRef.name // true
        mountainsRef.path == mountainImagesRef.path // false
    }

    fun chooseFileFromInnerData(){
        val getContent = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uri: List<@JvmSuppressWildcards Uri>? ->
            // Handle the returned Uri
        }
        getContent.launch("image/*")
        val intent = Intent(Intent.ACTION_VIEW).setType("image/*")
        startActivityForResult(intent, 123)
    }

    fun pickUp(){

        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))


//        // Registers a photo picker activity launcher in single-select mode.
//        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//            // Callback is invoked after the user selects a media item or closes the
//            // photo picker.
//            if (uri != null) {
//                Log.d("PhotoPicker", "Selected URI: $uri")
//            } else {
//                Log.d("PhotoPicker", "No media selected")
//            }
//        }

// Include only one of the following calls to launch(), depending on the types
// of media that you want to let the user choose from.

// Launch the photo picker and let the user choose images and videos.
//        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))

// Launch the photo picker and let the user choose only images.
//        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

// Launch the photo picker and let the user choose only videos.
//        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))

// Launch the photo picker and let the user choose only images/videos of a
// specific MIME type, such as GIFs.
//        val mimeType = "image/gif"
//        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.SingleMimeType(mimeType)))
    }
}