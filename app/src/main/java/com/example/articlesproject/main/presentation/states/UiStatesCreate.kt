package com.example.articlesproject.main.presentation.states

import com.example.articlesproject.main.data.data.DishData

sealed class UiStatesCreate{
    object ToChoosePicture: UiStatesCreate()
    data class ToAddDish(val dish: DishData, val index: Int): UiStatesCreate()
    data class ToAddDishType(val type: String): UiStatesCreate()
    object ToSaveMenu: UiStatesCreate()
}
