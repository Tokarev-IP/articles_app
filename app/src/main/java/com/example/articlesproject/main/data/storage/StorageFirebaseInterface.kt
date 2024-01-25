package com.example.articlesproject.main.data.storage

import android.net.Uri

interface StorageFirebaseInterface {
    fun uploadFileFirebase(
        uri: Uri,
        dishId: String,
        onSuccessful: () -> Unit,
        onError:( exception: Exception) -> Unit,
        )
    fun downloadFileFirebase(fullUrl: String)
}