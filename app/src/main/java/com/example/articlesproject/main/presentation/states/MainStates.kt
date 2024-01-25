package com.example.articlesproject.main.presentation.states

import android.net.Uri
import com.example.articlesproject.main.data.firestore.data.DishDataFirestore
import com.example.articlesproject.main.data.firestore.data.FirestoreData
import com.example.articlesproject.main.data.firestore.data.MenuDataFirestore
import com.example.articlesproject.main.data.firestore.data.TypeDataFirestore

sealed class UiIntents {
    data class ToAddPicture(val uri: Uri, val dishId: String) : UiIntents()
    data class ToAddDish(val dishData: DishDataFirestore) : UiIntents()
    data class ToAddType(val typeData: TypeDataFirestore) : UiIntents()
    data class ToAddMenu(val menu: MenuDataFirestore) : UiIntents()
    object ToCreateMenu : UiIntents()
    object GetDataIdFromFirestore : UiIntents()
    object GetTypeDataListFromFirestore : UiIntents()
    object GetDishDataListFromFirestore: UiIntents()
    object GetDataMenuFromFirestore : UiIntents()
}

interface UiStates {
    object Loading : UiStates
    object Showing : UiStates
    object Error : UiStates
    object NullData: UiStates
}

interface ScreenStates {
    object CreateDataIdScreen : ScreenStates
    object CreateDishGridScreen : ScreenStates
    object CreateDishItemScreen : ScreenStates
    object CreateMenuScreen : ScreenStates
    object CreateTypeScreen : ScreenStates
    object ShowDishGridScreen : ScreenStates
}

interface GetDataEventStates{
    data class FailureGettingData(val errorMsg: String) : GetDataEventStates
    data class CompleteGettingDocumentData(val data: FirestoreData) : GetDataEventStates
    data class CompleteGettingListData(val dataList: List<FirestoreData>) : GetDataEventStates
    object NullGettingData : GetDataEventStates
}

sealed interface SetDataEventStates {
    data class FailureSettingData(val errorMsg: String) : SetDataEventStates
    data class CompleteSettingData(val msg: String) : SetDataEventStates
}
