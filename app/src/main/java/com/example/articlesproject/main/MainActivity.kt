package com.example.articlesproject.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.articlesproject.login.domain.usecases.FirebaseAuthUseCase
import com.example.articlesproject.main.data.CompressPicture
import com.example.articlesproject.main.presentation.MainActivityCompose
import com.example.articlesproject.theme.ArticlesProjectTheme
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var firebaseAuthUseCase: FirebaseAuthUseCase

    private val storage = Firebase.storage
    private var storageRef = storage.reference
    private var imagesRef: StorageReference =
        storageRef.child("gs://articles-app-8b12b.appspot.com/photo.jpg")
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

    }

    @Composable
    fun StartUi() {
        val mainViewModel = hiltViewModel<MainViewModel>()

        MainActivityCompose(
            mainViewModel = mainViewModel,
            onSend = {sendPicture()},
        )

//        TextCompose(
//            onChooseFile = {
//                pickUp()
//            },
//            mainViewModel = mainViewModel,
//            onSendPhoto = {
//                    storage.getReference("photos/image/").child("/path/").putFile(mainViewModel.uri)
//                        .addOnSuccessListener {
//                            Log.d("MYTAG_LOAD", "loadSuccess")
//                        }
//                        .addOnCompleteListener {
//                            Log.d("MYTAG_LOAD", "loadSuccess")
//                        }
//                        .addOnFailureListener {
//                            Log.d("MYTAG_LOAD", "loadFailed + $it")
//                        }

//                    storageRef.putFile(mainViewModel.uri)
//                        .addOnSuccessListener {
//                            Log.d("MYTAG_LOAD", "loadSuccess")
//                        }
//                        .addOnFailureListener {
//                            Log.d("MYTAG_LOAD", "loadFailed + $it")
//                        }
//            }
//        )
    }

    fun setUrl() {
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

    fun chooseFileFromInnerData() {
        val getContent =
            registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uri: List<@JvmSuppressWildcards Uri>? ->
                // Handle the returned Uri
            }
        getContent.launch("image/*")
        val intent = Intent(Intent.ACTION_VIEW).setType("image/*")
        startActivityForResult(intent, 123)
    }

    private fun pickUp() {
//        pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//            // Callback is invoked after the user selects a media item or closes the
//            // photo picker.
//            if (uri != null) {
//                Log.d("PhotoPicker", "Selected URI: $uri")
//                Log.d("PhotoPicker", "Selected URI: ${uri.path}")
//                mainViewModel.setImageUri(uri)
//            } else {
//                Log.d("PhotoPicker", "No media selected")
//            }
//        }

        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
    }

    fun sendPicture() {
        val userId = firebaseAuthUseCase.getAuthId()

//        userId?.let {userIdString ->
//            storage.getReference("photos/image/").child(userIdString).putFile(mainViewModel.uri)
//                .addOnSuccessListener {
//                    Log.d("MYTAG_LOAD", "loadSuccess")
//                }
//                .addOnCompleteListener {
//                    Log.d("MYTAG_LOAD", "loadSuccess")
//                }
//                .addOnFailureListener {
//                    Log.d("MYTAG_LOAD", "loadFailed + $it")
//                }
//        }

        userId?.let {userIdString ->
            storage.getReference("photos/image/").child(userIdString).putBytes(
                CompressPicture().compressImage(this,mainViewModel.uri, 1920, 1080,50)
            )
                .addOnSuccessListener {
                    Log.d("MYTAG_LOAD", "loadSuccess")
                }
                .addOnCompleteListener {
                    Log.d("MYTAG_LOAD", "loadSuccess")
                }
                .addOnFailureListener {
                    Log.d("MYTAG_LOAD", "loadFailed + $it")
                }
        }

//        storageRef.putFile(mainViewModel.uri)
//            .addOnSuccessListener {
//                Log.d("MYTAG_LOAD", "loadSuccess")
//            }
//            .addOnFailureListener {
//                Log.d("MYTAG_LOAD", "loadFailed + $it")
//            }
    }

}