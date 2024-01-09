package com.example.articlesproject.main.data.data.firestore.interfaces

interface FirestoreSetDataInterface {
    fun <T : Any> setData(
        data: T,
        path: String,
        documentId: String,
        authId: String,
        onComplete: (msg: String) -> Unit,
        onFailure: (e: Exception) -> Unit,
    )
}