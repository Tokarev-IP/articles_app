package com.example.articlesproject.main.data.firestore

import android.util.Log
import com.example.articlesproject.main.data.firestore.interfaces.FirestoreGetDataListenerInterface
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreGetGetDataListener @Inject constructor(

) : FirestoreGetDataListenerInterface {

    private val firestoreDatabase = Firebase.firestore

    override fun getListenerFromTwoCollection(
        collection1: String,
        collection2: String,
        dataId: String,
        onSuccess: (result: QuerySnapshot) -> Unit,
        onFailure: (e: FirebaseFirestoreException) -> Unit
    ) {
        firestoreDatabase.collection(collection1).document(dataId).collection(collection2)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.d("DAVAI", "Listener data ERROR")
                    onFailure(error)
                    return@addSnapshotListener
                }
                value?.let {newValue ->
                    onSuccess(newValue)
                    Log.d("DAVAI", "Data listener ${newValue.documents}")
                }
            }
    }
}