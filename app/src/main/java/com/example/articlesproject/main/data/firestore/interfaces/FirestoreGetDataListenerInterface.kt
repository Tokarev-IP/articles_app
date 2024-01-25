package com.example.articlesproject.main.data.firestore.interfaces

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

interface FirestoreGetDataListenerInterface {

    fun getListenerFromTwoCollection(
        collection1: String,
        collection2: String,
        dataId: String,
        onSuccess: (result: QuerySnapshot) -> Unit,
        onFailure: (e: FirebaseFirestoreException) -> Unit
    )
}