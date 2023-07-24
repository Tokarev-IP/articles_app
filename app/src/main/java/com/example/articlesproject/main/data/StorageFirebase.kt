package com.example.articlesproject.main.data

import android.net.Uri
import com.example.articlesproject.main.domain.interfaces.StorageFirebaseInterface
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class StorageFirebase : StorageFirebaseInterface {

    private val storage = Firebase.storage

    override fun uploadFileFirebase(location: String, pathString: String, uri: Uri) {
        storage.getReference(location).child(pathString).putFile(uri)
            .addOnCompleteListener {
                if (it.isSuccessful) {

                } else {
                    when (it.exception) {

                    }
                }
            }
    }

    override fun downloadFileFirebase(fullUrl: String) {
        storage.getReferenceFromUrl(fullUrl)
    }

    sealed interface StorageFirebaseData {

        object Complete : StorageFirebaseData
        data class Exception(val item: kotlin.Exception)

    }
}