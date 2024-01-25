package com.example.articlesproject.main.data.firestore.data

import com.google.firebase.firestore.PropertyName

open class FirestoreData
data class DishDataFirestore @JvmOverloads constructor(
    @get:PropertyName("name")
    @set:PropertyName("name")
    var name: String = "",

    @get:PropertyName("price")
    @set:PropertyName("price")
    var price: Double = 0.0,

    @get:PropertyName("priority")
    @set:PropertyName("priority")
    var priority: Int = 0,

    @get:PropertyName("isPicture")
    @set:PropertyName("isPicture")
    var isPicture: Boolean = false,

    @get:PropertyName("id")
    @set:PropertyName("id")
    var id: String = "",

    @get:PropertyName("typeId")
    @set:PropertyName("typeId")
    var typeId: String = "",
): FirestoreData()

data class TypeDataFirestore @JvmOverloads constructor(
    @get:PropertyName("name")
    @set:PropertyName("name")
    var name: String = "",

    @get:PropertyName("id")
    @set:PropertyName("id")
    var id: String = "",

    @get:PropertyName("priority")
    @set:PropertyName("priority")
    var priority: Int = 0,

    @get:PropertyName("menuId")
    @set:PropertyName("menuId")
    var menuId: String = "",
): FirestoreData()

data class MenuDataFirestore @JvmOverloads constructor(
    @get:PropertyName("name")
    @set:PropertyName("name")
    var name: String = "",

    @get:PropertyName("id")
    @set:PropertyName("id")
    var id: String = "",

    @get:PropertyName("priority")
    @set:PropertyName("priority")
    var priority: Int = 0,
): FirestoreData()

data class DataIdFirestore @JvmOverloads constructor(
    @get:PropertyName("id")
    @set:PropertyName("id")
    var id: String = "",
): FirestoreData()

data class MenuData(
    val typeData: TypeDataFirestore,
    val dishesList: List<DishDataFirestore>,
)