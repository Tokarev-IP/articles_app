package com.example.articlesproject.main.data.firestore

import android.util.Log
import com.example.articlesproject.main.data.firestore.interfaces.FirestoreGetDataInterface
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreGetDataRepository @Inject constructor() : FirestoreGetDataInterface {

    private val firestoreDatabase = Firebase.firestore

    override fun getAnyDataFromOneCollection(
        collection: String,
        onSuccess: (result: QuerySnapshot) -> Unit,
        onFailure: (e: Exception) -> Unit
    ) {
        Log.d("DAVAI", "Get two collection Repository")
        firestoreDatabase.collection(collection)
            .get()
            .addOnSuccessListener { document ->
                Log.d("DAVAI", "success of getting data id ${document} => ${document.documents}")
                onSuccess(document)
            }
            .addOnFailureListener { exception ->
                Log.d("DAVAI", "Error getting documents. $exception")
                onFailure(exception)
            }
    }

    override fun getDocumentFromOneCollection(
        collection: String,
        documentId: String,
        onSuccess: (result: DocumentSnapshot) -> Unit,
        onFailure: (e: Exception) -> Unit
    ) {
        Log.d("DAVAI", "Get one collection Repository")
        firestoreDatabase.collection(collection).document(documentId)
            .get()
            .addOnSuccessListener { document ->
                Log.d("DAVAI", "sucess menu id ${document} => ${document.data}")
                onSuccess(document)
            }
            .addOnFailureListener { exception ->
                Log.w("DAVAI", "Error getting documents.", exception)
                onFailure(exception)
            }
    }

    override fun getAnyDataFromTwoCollection(
        collection1: String,
        collection2: String,
        dataId: String,
        onSuccess: (result: QuerySnapshot) -> Unit,
        onFailure: (e: Exception) -> Unit
    ) {
        firestoreDatabase.collection(collection1).document(dataId).collection(collection2)
            .get()
            .addOnSuccessListener { document ->
                Log.d("DAVAI", "${document} => ${document.documents}")
                onSuccess(document)
            }
            .addOnFailureListener { exception ->
                Log.w("DAVAI", "Error getting documents.", exception)
                onFailure(exception)
            }
    }

    override fun getDocumentFromTwoCollection(
        collection1: String,
        collection2: String,
        dataId: String,
        documentId: String,
        onSuccess: (result: DocumentSnapshot) -> Unit,
        onFailure: (e: Exception) -> Unit
    ) {
        firestoreDatabase.collection(collection1).document(dataId).collection(collection2).document()
            .get()
            .addOnSuccessListener { document ->
                Log.d("DAVAI", "${document} => ${document.data}")
                onSuccess(document)
            }
            .addOnFailureListener { exception ->
                Log.w("DAVAI", "Error getting documents.", exception)
                onFailure(exception)
            }
    }
}