package com.example.articlesproject.main.domain.usecases.interfaces

import com.example.articlesproject.main.data.firestore.data.DataIdFirestore
import com.example.articlesproject.main.data.firestore.data.DishDataFirestore
import com.example.articlesproject.main.data.firestore.data.MenuDataFirestore
import com.example.articlesproject.main.data.firestore.data.TypeDataFirestore

interface FirestoreGetDataUseCaseInterface {

    fun getTypeListData(
        collection1: String = "data",
        collection2: String = "type",
        dataId: String,
        onSuccess: (typeListData: List<TypeDataFirestore>) -> Unit,
        onNullDocument: () -> Unit,
        onFailure: (e: String) -> Unit,
    )

    fun getDishListData(
        collection1: String = "data",
        collection2: String = "dish",
        dataId: String,
        onSuccess: (typeListData: List<DishDataFirestore>) -> Unit,
        onNullDocument: () -> Unit,
        onFailure: (e: String) -> Unit,
    )

    fun getMenuData(
        collection1: String = "data",
        collection2: String = "menu",
        dataId: String,
        onSuccess: (typeListData: List<MenuDataFirestore>) -> Unit,
        onNullDocument: () -> Unit,
        onFailure: (e: String) -> Unit,
    )

    fun getMenuIdDocument(
        collection: String = "id",
        onSuccess: (dataId: DataIdFirestore) -> Unit,
        onNullDocument:() -> Unit,
        onFailure: (e: String) -> Unit,
    )
}