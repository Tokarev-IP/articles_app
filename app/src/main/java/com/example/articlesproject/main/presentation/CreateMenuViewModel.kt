package com.example.articlesproject.main.presentation

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.articlesproject.main.data.data.DishDataFirestore
import com.example.articlesproject.main.data.data.MenuData
import com.example.articlesproject.main.data.data.TypeDataFirestore
import com.example.articlesproject.main.domain.usecases.interfaces.FirestoreSetDataUseCaseInterface
import com.example.articlesproject.main.presentation.states.UiIntents
import com.example.articlesproject.main.presentation.states.UiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateMenuViewModel @Inject constructor(
    private val firestoreSetDataUseCaseInterface: FirestoreSetDataUseCaseInterface
) : ViewModel() {

    private val menuDataList: MutableStateFlow<MutableList<MenuData>> =
        MutableStateFlow(mutableStateListOf())
    private val menuDataListFlow = menuDataList.asStateFlow()

    private val uiStates: MutableStateFlow<UiStates> = MutableStateFlow(UiStates.Showing)
    private val uiStatesFlow = uiStates.asStateFlow()

    private val uriOfPicture: MutableStateFlow<Uri?> = MutableStateFlow(null)
    private val uriOfPictureFlow = uriOfPicture.asStateFlow()

    fun getMenuDataListFlow() = menuDataListFlow
    fun getUiStatesFlow() = uiStatesFlow
    fun getUriOfPictureFlow() = uriOfPictureFlow
    private fun setType(typeData: TypeDataFirestore) {
        menuDataList.value.add(MenuData(typeData, mutableListOf()))
    }

    private fun setUriOfPicture(uri: Uri?) {
        uriOfPicture.value = uri
    }

    private fun setUiState(uiState: UiStates) {
        uiStates.value = uiState
    }

    fun setIntent(uiIntent: UiIntents) {
        when (uiIntent) {
            is UiIntents.ToAddDish -> {
                uiStates.value = UiStates.Loading
                setDishData(uiIntent.itemData)
            }

            is UiIntents.ToChoosePicture -> {
                setUriOfPicture(uiIntent.uri)
            }

            is UiIntents.ToAddType -> {
                uiStates.value = UiStates.Loading
                setTypeData(uiIntent.typeData)
            }
        }
    }

    private fun setTypeData(
        typeData: TypeDataFirestore,
    ) {
        firestoreSetDataUseCaseInterface.setTypeData(
            typeData = typeData,
            onComplete = {
                setType(typeData)
                setUiState(UiStates.Info(it))
            },
            onFailure = {
                setUiState(UiStates.Info(it))
            },
        )
    }

    private fun setDishData(
        dishData: DishDataFirestore,
    ) {
        firestoreSetDataUseCaseInterface.setDishData(
            dishData = dishData,
            onComplete = {
                setUiState(UiStates.Info(it))
            },
            onFailure = {
                setUiState(UiStates.Info(it))
            },
        )
    }
}