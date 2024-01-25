package com.example.articlesproject.main.data.firestore.interfaces

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface FirestoreGetDataInterface {

    fun getAnyDataFromOneCollection(
        collection: String,
        onSuccess: (result: QuerySnapshot) -> Unit,
        onFailure: (e: Exception) -> Unit,
    )

    fun getDocumentFromOneCollection(
        collection: String,
        documentId: String,
        onSuccess: (result: DocumentSnapshot) -> Unit,
        onFailure: (e: Exception) -> Unit,
    )

    fun getAnyDataFromTwoCollection(
        collection1: String,
        collection2: String,
        dataId: String,
        onSuccess: (result: QuerySnapshot) -> Unit,
        onFailure: (e: Exception) -> Unit,
    )

    fun getDocumentFromTwoCollection(
        collection1: String,
        collection2: String,
        dataId: String,
        documentId: String,
        onSuccess: (result: DocumentSnapshot) -> Unit,
        onFailure: (e: Exception) -> Unit,
    )

}