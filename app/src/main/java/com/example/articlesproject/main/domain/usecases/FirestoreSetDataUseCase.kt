package com.example.articlesproject.main.domain.usecases

import android.util.Log
import com.example.articlesproject.login.data.interfaces.FirebaseAuthInterface
import com.example.articlesproject.main.data.firestore.data.DataIdFirestore
import com.example.articlesproject.main.data.firestore.data.DishDataFirestore
import com.example.articlesproject.main.data.firestore.data.MenuDataFirestore
import com.example.articlesproject.main.data.firestore.data.TypeDataFirestore
import com.example.articlesproject.main.data.firestore.interfaces.FirestoreSetDataInterface
import com.example.articlesproject.main.domain.usecases.interfaces.FirestoreSetDataUseCaseInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreSetDataUseCase @Inject constructor(
    private val firestoreSetDataInterface: FirestoreSetDataInterface,
    private val firebaseAuthInterface: FirebaseAuthInterface,
) : FirestoreSetDataUseCaseInterface {
    override fun setTypeData(
        typeData: TypeDataFirestore,
        onComplete: (msg: String) -> Unit,
        onFailure: (e: String) -> Unit,
        collection1: String,
        collection2: String,
        dataId: String,
    ) {
        firestoreSetDataInterface.setAnyDataTwoCollection(
            data = typeData,
            collection1 = collection1,
            collection2 = collection2,
            dataId = dataId,
            documentId = typeData.id,
            onComplete = { onComplete(it) },
            onFailure = { onFailure(it.message.toString()) },
        )
    }

    override fun setDishData(
        dishData: DishDataFirestore,
        onComplete: (msg: String) -> Unit,
        onFailure: (e: String) -> Unit,
        collection1: String,
        collection2: String,
        dataId: String,
    ) {
        firestoreSetDataInterface.setAnyDataTwoCollection(
            data = dishData,
            collection1 = collection1,
            collection2 = collection2,
            dataId = dataId,
            documentId = dishData.id,
            onComplete = { onComplete(it) },
            onFailure = { onFailure(it.message.toString()) },
        )
    }

    override fun setMenuData(
        menuData: MenuDataFirestore,
        onComplete: (msg: String) -> Unit,
        onFailure: (e: String) -> Unit,
        collection1: String,
        collection2: String,
        dataId: String
    ) {
        firestoreSetDataInterface.setAnyDataTwoCollection(
            data = menuData,
            collection1 = collection1,
            collection2 = collection2,
            dataId = dataId,
            documentId = menuData.id,
            onComplete = { onComplete(it) },
            onFailure = { onFailure(it.message.toString()) },
        )
    }

    override fun setDataId(
        dataId: String,
        onComplete: (msg: String) -> Unit,
        onFailure: (e: String) -> Unit,
        collection: String,
    ) {
        Log.d("DAVAI", "set Id use case")
        val authId = firebaseAuthInterface.getAuthId()
        if (authId != null)
            firestoreSetDataInterface.setAnyDataOneCollection(
                data = DataIdFirestore(dataId),
                collection = collection,
                documentId = authId,
                onComplete = { onComplete(it) },
                onFailure = { onFailure(it.message.toString()) },
            )
    }
}