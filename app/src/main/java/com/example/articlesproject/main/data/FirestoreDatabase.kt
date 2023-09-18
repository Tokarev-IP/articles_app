package com.example.articlesproject.main.data

import android.content.ContentValues.TAG
import android.util.Log
import com.example.articlesproject.main.data.data.DishData
import com.example.articlesproject.main.data.data.MenuData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreDatabase {

    private val firestoreDatabase = Firebase.firestore
    fun uploadTypesOfMenuData(
        collectionPath: String,
        documentId: String,
//        menuData: List<MenuData>
    ) {
        val data =  listOf(
            MenuData(
                "Salad", mutableListOf(
                    DishData(null, "Olevie", 150, "Very tasty salad"),
                    DishData(null, "Shuba", 170, "Very tasty salad"),
                    DishData(null, "Vinegret", 130, "Very tasty salad"),
                    DishData(null, "Craboviy", 140, "Very tasty salad"),
                    DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
                )
            ),
            MenuData(
                "Soup", mutableListOf(
                    DishData(null, "Borsh", 150, "Very tasty salad"),
                    DishData(null, "Goroh", 170, "Very tasty salad"),
                    DishData(null, "Shi", 130, "Very tasty salad"),
                    DishData(null, "Rassolnik", 140, "Very tasty salad"),
                    DishData(null, "Vermishel", 120, "Very tasty salad"),
                )
            )
        )


        Firebase.firestore.collection(collectionPath).document(documentId)
            .set(data)
            .addOnSuccessListener { documentReference ->
                Log.d("DAVAI", "DocumentSnapshot added with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("DAVAI", "Error adding document", e)
            }

//        firestoreDatabase.collection("userId")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    Log.d(TAG, "${document.id} => ${document.data}")
//                }
//            }
    }
}