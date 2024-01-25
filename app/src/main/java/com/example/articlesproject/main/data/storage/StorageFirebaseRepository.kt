package com.example.articlesproject.main.data.storage

import android.net.Uri
import com.example.articlesproject.login.data.interfaces.FirebaseAuthInterface
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageFirebaseRepository @Inject constructor(
    private val firebaseAuthInterface: FirebaseAuthInterface,
) : StorageFirebaseInterface {

    private val storage = Firebase.storage

    override fun uploadFileFirebase(
        uri: Uri,
        dishId: String,
        onSuccessful: () -> Unit,
        onError: (exception: Exception) -> Unit,
    ) {
        val authId = firebaseAuthInterface.getAuthId()
        authId?.let { id ->
            storage.getReference(id).child(dishId).putFile(uri)
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