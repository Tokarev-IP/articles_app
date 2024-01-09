package com.example.articlesproject.main.domain.usecases.interfaces

import com.example.articlesproject.main.data.data.DishDataFirestore
import com.example.articlesproject.main.data.data.TypeDataFirestore

interface FirestoreSetDataUseCaseInterface {
    fun setTypeData(
        typeData: TypeDataFirestore,
        onComplete: (msg: String) -> Unit,
        onFailure: (e: String) -> Unit,
        path: String = "types",
    )

    fun setDishData(
        dishData: DishDataFirestore,
        onComplete: (msg: String) -> Unit,
        onFailure: (e: String) -> Unit,
        path: String = "dishes",
    )
}