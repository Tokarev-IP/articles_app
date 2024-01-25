package com.example.articlesproject.main.data.firestore

import android.util.Log
import com.example.articlesproject.main.data.firestore.interfaces.FirestoreSetDataInterface
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreSetDataRepository @Inject constructor() : FirestoreSetDataInterface {

    private val firestoreDatabase = Firebase.firestore

    override fun <T : Any> setAnyDataOneCollection(
        data: T,
        collection: String,
        documentId: String,
        onComplete: (msg: String) -> Unit,
        onFailure: (e: Exception) -> Unit
    ) {
        Log.d("DAVAI", "Set Any Data repository data - $data")
        firestoreDatabase.collection(collection).document(documentId)
            .set(data)
            .addOnCompleteListener {
                Log.d("DAVAI", "Complete Listener ${it.result} and ${it.isComplete}")
                onComplete("Data was uploaded successfully")
            }
            .addOnFailureListener { e ->
                Log.w("DAVAI", "Error", e)
                onFailure(e)
            }
    }

    override fun <T : Any> setAnyDataTwoCollection(
        data: T,
        collection1: String,
        collection2: String,
        dataId: String,
        documentId: String,
        onComplete: (msg: String) -> Unit,
        onFailure: (e: Exception) -> Unit
    ) {
        firestoreDatabase.collection(collection1).document(dataId).collection(collection2).document(documentId)
            .set(data)
            .addOnCompleteListener {
                Log.d("DAVAI", "Complete Listener ${it.result} and ${it.isComplete}")
                onComplete("Data was uploaded successfully")
            }
            .addOnFailureListener { e ->
                Log.w("DAVAI", "Error", e)
                onFailure(e)
            }
    }
}

//    fun uploadTypesOfMenuData(
//        collectionPath: String,
//        documentId: String,
//        menuData: MenuData,
//    ) {
//        val menuData = MenuData(
//            "Salad", mutableListOf(
//                DishData(null, "Olevie", 1, "Very tasty salad"),
//                DishData(null, "Shuba", 17, "Very tasty salad"),
//                DishData(null, "Vinegret", 1300, "Very tasty salad"),
//                DishData(null, "Craboviy", 1400, "Very tasty salad"),
//                DishData(null, "Ovoshnoy", 1200, "Very tasty salad"),
//                DishData(null, "Ovoshnoy", 1200, "Very tasty salad"),
//                DishData(null, "Ovoshnoy", 12, "Very tasty salad"),
//                DishData(null, "Ovoshnoy", 120, "Very tasty salad"),
//                DishData(null, "Ovoshnoy", 1200, "Very tasty salad na den rogdenia"),
//                DishData(null, "Tikva", 12, "Very tasty salad"),
//            )
//        )

//        Firebase.firestore.collection(collectionPath).document(documentId)
//            .set(menuData)
//            .addOnSuccessListener { documentReference ->
//                Log.d("DAVAI", "DocumentSnapshot added with ID: $documentReference")
//            }
//            .addOnCompleteListener {
//                Log.d("DAVAI", "Complete Listener ${it.result} and ${it.isComplete}")
//            }
//            .addOnFailureListener { e ->
//                Log.w("DAVAI", "Error adding document", e)
//            }

//        firestoreDatabase.collection("path")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    Log.d("DAVAI", "${document.id} => ${document.data}")
//                }
//            }

//        firestoreDatabase.collection("path").document("id")
//            .get()
//            .addOnSuccessListener { document ->
//                    Log.d("DAVAI", "${document.id} => ${document.data}")
//            }
//    }

//    override fun uploadData(
//        collectionPath: String? = firebaseAuthUseCase.getAuthId(),
//        documentId: String,
//    ) {
//        Firebase.firestore.collection(collectionPath).document(documentId)
//            .set(Unit)
//            .addOnSuccessListener { documentReference ->
//                Log.d("DAVAI", "DocumentSnapshot added with ID: $documentReference")
//            }
//            .addOnCompleteListener {
//                Log.d("DAVAI", "Complete Listener ${it.result} and ${it.isComplete}")
//            }
//            .addOnFailureListener { e ->
//                Log.w("DAVAI", "Error adding document", e)
//            }
//    }

//}