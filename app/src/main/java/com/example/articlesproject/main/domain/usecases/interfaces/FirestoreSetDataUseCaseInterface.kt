package com.example.articlesproject.main.domain.usecases.interfaces

import com.example.articlesproject.main.data.firestore.data.DishDataFirestore
import com.example.articlesproject.main.data.firestore.data.MenuDataFirestore
import com.example.articlesproject.main.data.firestore.data.TypeDataFirestore

interface FirestoreSetDataUseCaseInterface {
    fun setTypeData(
        typeData: TypeDataFirestore,
        onComplete: (msg: String) -> Unit,
        onFailure: (e: String) -> Unit,
        collection1: String = "data",
        collection2: String = "type",
        dataId: String,
    )

    fun setDishData(
        dishData: DishDataFirestore,
        onComplete: (msg: String) -> Unit,
        onFailure: (e: String) -> Unit,
        collection1: String = "data",
        collection2: String = "dish",
        dataId: String,
    )

    fun setMenuData(
        menuData: MenuDataFirestore,
        onComplete: (msg: String) -> Unit,
        onFailure: (e: String) -> Unit,
        collection1: String = "data",
        collection2: String = "menu",
        dataId: String,
    )

    fun setDataId(
        dataId: String,
        onComplete: (msg: String) -> Unit,
        onFailure: (e: String) -> Unit,
        collection: String = "id",
    )
}