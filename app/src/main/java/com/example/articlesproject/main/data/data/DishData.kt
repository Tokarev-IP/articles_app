package com.example.articlesproject.main.data.data

import android.net.Uri

data class DishData(
    val pictureUri: Uri? = null,
    val name: String,
    val price: Int,
    val description: String,
)

data class DishTypeData(
    val type: String,
    val dishesList: List<DishData>
)