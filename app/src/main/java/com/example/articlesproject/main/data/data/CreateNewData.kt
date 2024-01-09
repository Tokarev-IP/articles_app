package com.example.articlesproject.main.data.data

import java.util.UUID

object CreateNewData {
    fun getNewDish(
        typeId: String,
        initialIsPicture: Boolean = false,
        initialName: String = "Name",
        initialPrice: Float = 0f,
        initialPriority: Byte = 1,
    ) = DishDataFirestore(
        name = initialName,
        price = initialPrice,
        priority = initialPriority,
        isPicture = initialIsPicture,
        id = getUUID(),
        typeId = typeId,
    )

    fun getNewType(
        initialName: String = "Type",
        initialPriority: Byte = 1,
    ) = TypeDataFirestore(
        name = initialName,
        id = getUUID(),
        priority = initialPriority,
    )

    private fun getUUID(): String = UUID.randomUUID().toString()
}