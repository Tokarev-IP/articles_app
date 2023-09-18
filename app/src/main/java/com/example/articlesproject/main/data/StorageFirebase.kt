package com.example.articlesproject.main.data

import android.net.Uri
import android.util.Log
import com.example.articlesproject.login.domain.interfaces.FirebaseAuthInterface
import com.example.articlesproject.main.domain.interfaces.StorageFirebaseInterface
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageFirebase @Inject constructor(
    private val firebaseAuthInterface: FirebaseAuthInterface,
) : StorageFirebaseInterface {

    private val storage = Firebase.storage

    override fun uploadFileFirebase(
        uri: Uri,
        onSuccessful: () -> Unit,
        onError: (exception: Exception) -> Unit,
    ) {
        val userId = firebaseAuthInterface.getAuthId()
        userId?.let { id ->
            storage.getReference(id).child(uri.toString()).putFile(uri)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        onSuccessful()
                    } else {
                        it.exception?.let { error -> onError(error) }
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