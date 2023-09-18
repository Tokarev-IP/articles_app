package com.example.articlesproject.main.presentation

import android.net.Uri
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
    private val uriOfPicture: MutableStateFlow<Uri?> = MutableStateFlow(null)
    private val uriOfPictureFlow = uriOfPicture.asStateFlow()

    private fun addDishType(type: String) {
        val data = MenuData(type, mutableListOf())
        menuDataList.value.add(data)
    }

    private fun addDishData(dish: DishData, index: Int) {
        menuDataList.value[index].dishesList.add(dish)
    }

    fun getMenuDataListFlow() = menuDataListFlow

    fun getUriOfPictureFlow() = uriOfPictureFlow
    private fun setUriOfPicture(uri: Uri?) {
        uriOfPicture.value = uri
    }

    fun setIntent(uiIntent: UiStatesCreate) {
        when (uiIntent) {
            is UiStatesCreate.ToAddDishType -> {
                addDishType(uiIntent.type)
            }

            is UiStatesCreate.ToAddDish -> {
                addDishData(uiIntent.dish, uiIntent.index)
            }

            is UiStatesCreate.ToSaveMenu -> {

            }

            is UiStatesCreate.ToChoosePicture -> {
                setUriOfPicture(uiIntent.uri)
            }
        }
    }

    fun testLoad(){

    }
}