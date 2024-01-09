package com.example.articlesproject.main.data.data

//data class DishData(
//    var pictureUri: Uri? = null,
//    var name: String,
//    var price: Int,
//    var description: String,
//)
//
//data class TypeData(
//    var type: String,
//    val id: String,
//)

data class DishDataFirestore(
    val name: String,
    val price: Float,
    val priority: Byte,
    val isPicture: Boolean,
    val id: String,
    val typeId: String,
)

data class TypeDataFirestore(
    val name: String,
    val id: String,
    val priority: Byte,
)

data class MenuData(
    val typeData: TypeDataFirestore,
    val dishesList: List<DishDataFirestore>,
)