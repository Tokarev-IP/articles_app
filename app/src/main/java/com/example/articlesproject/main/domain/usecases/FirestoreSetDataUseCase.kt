package com.example.articlesproject.main.domain.usecases

import com.example.articlesproject.login.data.interfaces.FirebaseAuthInterface
import com.example.articlesproject.main.data.data.DishDataFirestore
import com.example.articlesproject.main.data.data.TypeDataFirestore
import com.example.articlesproject.main.data.data.firestore.interfaces.FirestoreSetDataInterface
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
        path: String
    ) {
        val authId = getAuthId()
        if (authId != null)
            firestoreSetDataInterface.setData(
                data = typeData,
                path = path,
                documentId = typeData.id,
                authId = authId,
                onComplete = { onComplete(it) },
                onFailure = { onFailure(it.message.toString()) },
            )
        else
            onFailure("Auth Id Error. Check your account")
    }

    override fun setDishData(
        dishData: DishDataFirestore,
        onComplete: (msg: String) -> Unit,
        onFailure: (e: String) -> Unit,
        path: String
    ) {
        val authId = getAuthId()
        if (authId != null)
            firestoreSetDataInterface.setData(
                data = dishData,
                path = path,
                documentId = dishData.id,
                authId = authId,
                onComplete = { onComplete(it) },
                onFailure = { onFailure(it.message.toString()) },
            )
        else
            onFailure("Auth Id Error. Check your account")
    }

    private fun getAuthId() = firebaseAuthInterface.getAuthId()
}