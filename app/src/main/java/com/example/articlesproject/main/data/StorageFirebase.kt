package com.example.articlesproject.main.data

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class StorageFirebase {

    private val storage = Firebase.storage

    fun uploadFileFirebase(location: String, pathString: String, uri: Uri){
        storage.getReference(location).child(pathString).putFile(uri)
            .addOnCompleteListener{
                if (it.isSuccessful)
            }
    }
}