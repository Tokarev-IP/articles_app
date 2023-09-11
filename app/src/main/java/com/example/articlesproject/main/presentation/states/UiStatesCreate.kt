package com.example.articlesproject.main.presentation.states

import android.net.Uri
import com.example.articlesproject.main.data.data.DishData

sealed class UiStatesCreate{
    data class ToChoosePicture(val uri: Uri?): UiStatesCreate()
    data class ToAddDish(val dish: DishData, val index: Int): UiStatesCreate()
    data class ToAddDishType(val type: String): UiStatesCreate()
    object ToSaveMenu: UiStatesCreate()
}
