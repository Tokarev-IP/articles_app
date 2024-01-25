package com.example.articlesproject.main.data.firestore.interfaces

interface FirestoreSetDataInterface {
    fun <T :  Any> setAnyDataOneCollection(
        data: T,
        collection: String,
        documentId: String,
        onComplete: (msg: String) -> Unit,
        onFailure: (e: Exception) -> Unit,
    )

    fun <T :  Any> setAnyDataTwoCollection(
        data: T,
        collection1: String,
        collection2: String,
        dataId: String,
        documentId: String,
        onComplete: (msg: String) -> Unit,
        onFailure: (e: Exception) -> Unit,
    )

}