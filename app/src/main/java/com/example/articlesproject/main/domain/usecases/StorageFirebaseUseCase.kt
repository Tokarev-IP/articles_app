package com.example.articlesproject.main.domain.usecases

import android.net.Uri
import com.example.articlesproject.main.domain.interfaces.StorageFirebaseInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageFirebaseUseCase @Inject constructor(private val storageFirebaseInterface: StorageFirebaseInterface) {

    fun uploadFileFirebase(location: String, pathString: String, uri: Uri) {
        storageFirebaseInterface.uploadFileFirebase(location, pathString, uri)
    }

    fun downloadFileFirebase(fullUrl: String) {
        storageFirebaseInterface.downloadFileFirebase(fullUrl)
    }
}