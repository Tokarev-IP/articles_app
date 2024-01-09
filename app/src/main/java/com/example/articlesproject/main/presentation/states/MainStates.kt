package com.example.articlesproject.main.presentation.states

import android.net.Uri
import com.example.articlesproject.main.data.data.DishDataFirestore
import com.example.articlesproject.main.data.data.TypeDataFirestore

sealed class UiIntents{
    data class ToChoosePicture(val uri: Uri?): UiIntents()
    data class ToAddDish(val itemData: DishDataFirestore): UiIntents()
    data class ToAddType(val typeData: TypeDataFirestore): UiIntents()
}

interface UiStates{
    object Loading: UiStates
    object Showing: UiStates
    data class Info(val msg: String): UiStates
}
