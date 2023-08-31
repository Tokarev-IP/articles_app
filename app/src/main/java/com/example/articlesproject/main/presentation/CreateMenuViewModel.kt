package com.example.articlesproject.main.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.articlesproject.main.data.data.DishData
import com.example.articlesproject.main.data.data.MenuData
import com.example.articlesproject.main.presentation.states.UiStatesCreate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateMenuViewModel @Inject constructor() : ViewModel() {

    private val menuDataList: MutableStateFlow<MutableList<MenuData>> =
        MutableStateFlow(mutableStateListOf())
    private val menuDataListFlow = menuDataList.asStateFlow()

    private fun toAddDishType(type: String) {
        val data = MenuData(type, mutableListOf())
        menuDataList.value.add(data)
    }

    private fun toAddDishData(dish: DishData, index: Int) {
        menuDataList.value[0].dishesList.add(dish)
    }

    fun getMenuDataListFlow() = menuDataListFlow

    fun setIntent(uiIntent: UiStatesCreate) {
        when (uiIntent) {
            is UiStatesCreate.ToAddDishType -> {
                toAddDishType(uiIntent.type)
            }

            is UiStatesCreate.ToAddDish -> {
                toAddDishData(uiIntent.dish, uiIntent.index)
            }

            is UiStatesCreate.ToSaveMenu -> {}
            is UiStatesCreate.ToChoosePicture -> {}
        }

    }
}