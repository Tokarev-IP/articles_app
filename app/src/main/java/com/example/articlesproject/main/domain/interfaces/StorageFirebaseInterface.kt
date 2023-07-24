package com.example.articlesproject.main.domain.interfaces

import android.net.Uri

interface StorageFirebaseInterface {

    fun uploadFileFirebase(location: String, pathString: String, uri: Uri)

    fun downloadFileFirebase(fullUrl: String)
}