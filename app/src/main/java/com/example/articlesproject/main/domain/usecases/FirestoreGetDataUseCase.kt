package com.example.articlesproject.main.domain.usecases

import android.util.Log
import com.example.articlesproject.login.data.interfaces.FirebaseAuthInterface
import com.example.articlesproject.main.data.firestore.data.DataIdFirestore
import com.example.articlesproject.main.data.firestore.data.DishDataFirestore
import com.example.articlesproject.main.data.firestore.data.MenuDataFirestore
import com.example.articlesproject.main.data.firestore.data.TypeDataFirestore
import com.example.articlesproject.main.data.firestore.interfaces.FirestoreGetDataInterface
import com.example.articlesproject.main.data.firestore.interfaces.FirestoreGetDataListenerInterface
import com.example.articlesproject.main.domain.usecases.interfaces.FirestoreGetDataUseCaseInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreGetDataUseCase @Inject constructor(
    private val firestoreGetDataInterface: FirestoreGetDataInterface,
    private val firestoreGetDataListenerInterface: FirestoreGetDataListenerInterface,
    private val firebaseAuthInterface: FirebaseAuthInterface,
) : FirestoreGetDataUseCaseInterface {

    override fun getTypeListData(
        collection1: String,
        collection2: String,
        dataId: String,
        onSuccess: (typeListData: List<TypeDataFirestore>) -> Unit,
        onNullDocument: () -> Unit,
        onFailure: (e: String) -> Unit
    ) {
        firestoreGetDataListenerInterface.getListenerFromTwoCollection(
            collection1 = collection1,
            collection2 = collection2,
            dataId = dataId,
            onSuccess = { result ->
                val typeDataList = mutableListOf<TypeDataFirestore>()
                for (documentSnapshot in result) {
                    typeDataList.add(
                        documentSnapshot.toObject(TypeDataFirestore::class.java)
                    )
                }
                Log.d("DAVAI", typeDataList.toString())
                if (typeDataList.isEmpty())
                    onNullDocument()
                else
                    onSuccess(typeDataList as List<TypeDataFirestore>)
            },
            onFailure = { e: Exception ->
                onFailure(e.message.toString())
            }
        )
    }

    override fun getDishListData(
        collection1: String,
        collection2: String,
        dataId: String,
        onSuccess: (typeListData: List<DishDataFirestore>) -> Unit,
        onNullDocument: () -> Unit,
        onFailure: (e: String) -> Unit
    ) {
        firestoreGetDataListenerInterface.getListenerFromTwoCollection(
            collection1 = collection1,
            collection2 = collection2,
            dataId = dataId,
            onSuccess = { result ->
                val dishDataList = mutableListOf<DishDataFirestore>()
                for (documentSnapshot in result) {
                    dishDataList.add(
                        documentSnapshot.toObject(DishDataFirestore::class.java)
                    )
                }
                Log.d("DAVAI", dishDataList.toString())
                if (dishDataList.isEmpty())
                    onNullDocument()
                else
                    onSuccess(dishDataList as List<DishDataFirestore>)
            },
            onFailure = { e: Exception ->
                onFailure(e.message.toString())
            }
        )
    }

    override fun getMenuData(
        collection1: String,
        collection2: String,
        dataId: String,
        onSuccess: (typeListData: List<MenuDataFirestore>) -> Unit,
        onNullDocument: () -> Unit,
        onFailure: (e: String) -> Unit
    ) {
        firestoreGetDataInterface.getAnyDataFromTwoCollection(
            collection1 = collection1,
            collection2 = collection2,
            dataId = dataId,
            onSuccess = { result ->
                val menuDataList = mutableListOf<MenuDataFirestore>()
                for (documentSnapshot in result) {
                    menuDataList.add(
                        documentSnapshot.toObject(MenuDataFirestore::class.java)
                    )
                }
                Log.d("DAVAI", menuDataList.toString())
                if (menuDataList.isEmpty())
                    onNullDocument()
                else
                    onSuccess(menuDataList as List<MenuDataFirestore>)
            },
            onFailure = { e: Exception ->
                onFailure(e.message.toString())
            }
        )
    }

    override fun getMenuIdDocument(
        collection: String,
        onSuccess: (dataId: DataIdFirestore) -> Unit,
        onNullDocument: () -> Unit,
        onFailure: (e: String) -> Unit
    ) {
        Log.d("DAVAI", "Get menu id Use case")
        val authId = firebaseAuthInterface.getAuthId()
        if (authId != null)
            firestoreGetDataInterface.getDocumentFromOneCollection(
                collection = collection,
                documentId = authId,
                onSuccess = { documentSnapshot ->
                    Log.d("DAVAI", "Menu Id to object")
                    val document = documentSnapshot.toObject(DataIdFirestore::class.java)
                    Log.d("DAVAI", "Menu Id string $document")
                    if (document != null) {
                        onSuccess(document)
                    } else
                        onNullDocument()
                },
                onFailure = { e: Exception ->
                    onFailure(e.message.toString())
                },
            )
    }

}

//    fun getListData(
//        dataId: String,
//        onSuccess: (listData: List<MenuData>) -> Unit,
//        onFailure: (e: String) -> Unit
//    ) {
//        val listData = mutableListOf<MenuData>()
//        var listType = listOf<TypeDataFirestore>()
//
//        getTypeListData(
//            dataId = dataId,
//            onSuccess = {
//                listType = it
//            },
//            onFailure = {}
//        )
//
//        getDishListData(
//            dataId = dataId,
//            onSuccess = { dishList ->
//                for (type in listType) {
//                    val dataList = dishList
//                    dataList.filter { dishData ->
//                        dishData.typeId == type.id
//                    }
//                    listData.add(
//                        MenuData(type, dataList)
//                    )
//                }
//                onSuccess(listData)
//            },
//            onFailure = {},
//        )
//    }

//}