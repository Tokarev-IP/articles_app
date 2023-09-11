package com.example.articlesproject.main.domain.interfaces

import android.net.Uri

interface StorageFirebaseInterface {
    fun uploadFileFirebase(
        uri: Uri,
        onSuccessful: () -> Unit,
        onError:( exception: Exception) -> Unit,
        )
    fun downloadFileFirebase(fullUrl: String)
}