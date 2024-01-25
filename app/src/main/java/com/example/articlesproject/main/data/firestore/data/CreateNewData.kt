package com.example.articlesproject.main.data.firestore.data

import java.util.UUID

object CreateNewData {
    fun getNewDish(
        typeId: String,
        initialIsPicture: Boolean = false,
        initialName: String = "Name",
        initialPrice: Double = 1.1,
        initialPriority: Int = 1,
    ) = DishDataFirestore(
        name = initialName,
        price = initialPrice,
        priority = initialPriority,
        isPicture = initialIsPicture,
        id = getUUID(),
        typeId = typeId,
    )

    fun getNewType(
        menuId: String,
        initialName: String = "Type",
        initialPriority: Int = 1,
    ) = TypeDataFirestore(
        name = initialName,
        id = getUUID(),
        priority = initialPriority,
        menuId = menuId,
    )

    fun getNewMenu(
        initialName: String = "Name",
        initialPriority: Int = 1,
    ) = MenuDataFirestore(
        name = initialName,
        id = getUUID(),
        priority = initialPriority,
    )
    private fun getUUID(): String = UUID.randomUUID().toString()
}