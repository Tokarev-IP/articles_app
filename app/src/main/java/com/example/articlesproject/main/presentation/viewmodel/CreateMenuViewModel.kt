package com.example.articlesproject.main.presentation.viewmodel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.articlesproject.main.data.firestore.data.DataIdFirestore
import com.example.articlesproject.main.data.firestore.data.DishDataFirestore
import com.example.articlesproject.main.data.firestore.data.MenuData
import com.example.articlesproject.main.data.firestore.data.MenuDataFirestore
import com.example.articlesproject.main.data.firestore.data.TypeDataFirestore
import com.example.articlesproject.main.presentation.states.GetDataEventStates
import com.example.articlesproject.main.presentation.states.ScreenStates
import com.example.articlesproject.main.presentation.states.SetDataEventStates
import com.example.articlesproject.main.presentation.states.UiIntents
import com.example.articlesproject.main.presentation.states.UiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class CreateMenuViewModel @Inject constructor(
    private val setDataMenu: SetDataMenu,
    private val getDataMenu: GetDataMenu,
) : ViewModel() {

    private val dataListState: MutableStateFlow<List<MenuData>> =
        MutableStateFlow(listOf())
    private val dataListFlow = dataListState.asStateFlow()

    private val menuDataList: MutableStateFlow<MutableList<MenuDataFirestore>> =
        MutableStateFlow(mutableStateListOf())
    private val menuDataListFlow = menuDataList.asStateFlow()


    private val uiStates: MutableStateFlow<UiStates> = MutableStateFlow(UiStates.Showing)
    private val uiStatesFlow = uiStates.asStateFlow()

    private val snackBarMsg: MutableStateFlow<String?> = MutableStateFlow(null)
    private val snackBarMsgFlow = snackBarMsg.asStateFlow()

    private val screenStates: MutableStateFlow<ScreenStates?> = MutableStateFlow(null)
    private val screenStatesFlow = screenStates.asStateFlow()

    private val uriOfPicture: MutableStateFlow<Uri?> = MutableStateFlow(null)
    private val uriOfPictureFlow = uriOfPicture.asStateFlow()


    private val dataId: MutableStateFlow<String> = MutableStateFlow("")

    private val typesMap: MutableMap<String, TypeDataFirestore> = mutableMapOf()
    private val dishesMap: MutableMap<String, DishDataFirestore> = mutableMapOf()

    fun getDataListFlow() = dataListFlow
    fun getUiStatesFlow() = uiStatesFlow
    fun getUriOfPictureFlow() = uriOfPictureFlow
    fun getScreenStateFlow() = screenStatesFlow
    fun getSnackBarMsgFlow() = snackBarMsgFlow
    fun getMenuListFlow() = menuDataListFlow
    fun setUiState(uiState: UiStates) {
        uiStates.value = uiState
    }

    fun setScreenState(screenState: ScreenStates) {
        screenStates.value = screenState
    }

    fun setUriOfPicture(uri: Uri?) {
        uriOfPicture.value = uri
    }

    fun setSnackBarMsg(msg: String) {
        snackBarMsg.value = msg
    }

    fun setIntent(uiIntent: UiIntents) {
        when (uiIntent) {
            is UiIntents.ToAddDish -> {
//                uiStates.value = UiStates.Loading
                setDataMenu.setDishData(
                    dishData = uiIntent.dishData,
                    dataId = dataId.value,
                    onEventState = { event: SetDataEventStates ->
                        when (event) {
                            is SetDataEventStates.CompleteSettingData -> {
//                                setUiState(UiStates.Showing)
                                setSnackBarMsg(event.msg)
                            }

                            is SetDataEventStates.FailureSettingData -> {
//                                setUiState(UiStates.Error)
                                setSnackBarMsg(event.errorMsg)
                            }
                        }
                    },
                )
            }

            is UiIntents.ToAddPicture -> {
                setUriOfPicture(uiIntent.uri)
            }

            is UiIntents.ToAddType -> {
//                uiStates.value = UiStates.Loading
                setDataMenu.setTypeData(
                    typeData = uiIntent.typeData,
                    dataId = dataId.value,
                    onEventState = { event: SetDataEventStates ->
                        when (event) {
                            is SetDataEventStates.CompleteSettingData -> {
//                                setUiState(UiStates.Showing)
                                setSnackBarMsg(event.msg)
                            }

                            is SetDataEventStates.FailureSettingData -> {
//                                setUiState(UiStates.Error)
                                setSnackBarMsg(event.errorMsg)
                            }
                        }
                    },
                )
            }

            is UiIntents.GetDataIdFromFirestore -> {
                Log.d("DAVAI", "Get data id intent")
                setUiState(UiStates.Loading)
                getDataMenu.getMenuId(
                    onDataEvent = { event: GetDataEventStates ->
                        when (event) {
                            is GetDataEventStates.CompleteGettingDocumentData -> {
                                (event.data as DataIdFirestore).let { dataIdFirestore: DataIdFirestore ->
                                    dataId.value = dataIdFirestore.id
                                    setScreenState(ScreenStates.CreateMenuScreen)
                                    setUiState(UiStates.Showing)
                                }
                            }

                            is GetDataEventStates.FailureGettingData -> {
                                setUiState(UiStates.Error)
                            }

                            is GetDataEventStates.NullGettingData -> {
                                setUiState(UiStates.NullData)
                            }
                        }
                    },
                )
            }

            is UiIntents.GetTypeDataListFromFirestore -> {
                setUiState(UiStates.Loading)

                val scope1 = viewModelScope

                getDataMenu.getTypeData(
                    dataId = dataId.value,
                    onDataEvent = { event: GetDataEventStates ->
                        when (event) {
                            is GetDataEventStates.CompleteGettingListData -> {
                                viewModelScope.launch {
                                    (event.dataList as List<TypeDataFirestore>).let { dataType ->
                                        scope1.launch {
                                            for (type in dataType) {
                                                typesMap[type.id] = type
                                            }
                                        }
                                        filterData()
                                    }
                                }
                                setUiState(UiStates.Showing)
                            }

                            is GetDataEventStates.FailureGettingData -> {

                            }

                            is GetDataEventStates.NullGettingData -> {
                                setUiState(UiStates.NullData)
                            }
                        }
                    }
                )

                getDataMenu.getDishData(
                    dataId = dataId.value,
                    onDataEvent = { event: GetDataEventStates ->
                        when (event) {
                            is GetDataEventStates.CompleteGettingListData -> {
                                viewModelScope.launch {
                                    (event.dataList as List<DishDataFirestore>).let { dataDish ->
                                        scope1.launch {
                                            for (dish in dataDish) {
                                                dishesMap[dish.id] = dish
                                            }
                                        }
                                        filterData()
                                    }
                                }
                                setUiState(UiStates.Showing)
                            }

                            is GetDataEventStates.FailureGettingData -> {

                            }
                        }
                    }
                )
            }

            is UiIntents.GetDishDataListFromFirestore -> {
//                setUiState(UiStates.Loading)
//
//                getDataMenu.getDishData(
//                    dataId = dataId.value,
//                    onDataEvent = { event: GetDataEventStates ->
//                        when (event) {
//                            is GetDataEventStates.CompleteGettingListData -> {
//                                viewModelScope.launch {
//                                    (event.dataList as List<DishDataFirestore>).let { dataDish ->
//                                        launch {
//                                            for (dish in dataDish) {
//                                                dishesMap[dish.id] = dish
//                                            }
//                                        }.join()
//                                        filterData()
//                                    }
//                                }
//                                setUiState(UiStates.Showing)
//                            }
//
//                            is GetDataEventStates.FailureGettingData -> {
//
//                            }
//                        }
//                    }
//                )
            }

            is UiIntents.GetDataMenuFromFirestore -> {
                setUiState(UiStates.Loading)
                getDataMenu.getMenuData(
                    dataId = dataId.value,
                    onDataEvent = { event: GetDataEventStates ->
                        when (event) {
                            is GetDataEventStates.CompleteGettingListData -> {
                                menuDataList.value.addAll(event.dataList as List<MenuDataFirestore>)
                                setUiState(UiStates.Showing)
                                Log.d("DAVAI", "data are got completely")
                            }

                            is GetDataEventStates.FailureGettingData -> {
                                setUiState(UiStates.Error)
                                Log.d("DAVAI", "data are got with error")
                            }

                            is GetDataEventStates.NullGettingData -> {
                                setUiState(UiStates.NullData)
                                Log.d("DAVAI", "NULL data are got completely")
                            }
                        }
                    }
                )
            }

            is UiIntents.ToAddMenu -> {
                setUiState(UiStates.Loading)
                setDataMenu.setMenuData(
                    menuData = uiIntent.menu,
                    dataId = dataId.value,
                    onEventState = { event: SetDataEventStates ->
                        when (event) {
                            is SetDataEventStates.CompleteSettingData -> {
                                menuDataList.value.add(uiIntent.menu)
                                setUiState(UiStates.Showing)
                            }

                            is SetDataEventStates.FailureSettingData -> {
                                setUiState(UiStates.Error)
                            }
                        }
                    }
                )
            }

            is UiIntents.ToCreateMenu -> {
                setUiState(UiStates.Loading)
                setDataMenu.setDataId(
                    onReturnDataId = { id: String ->
                        dataId.value = id
                    },
                    onEventState = { event: SetDataEventStates ->
                        when (event) {
                            is SetDataEventStates.CompleteSettingData -> {
                                setScreenState(ScreenStates.CreateMenuScreen)
                                setUiState(UiStates.Showing)
                            }

                            is SetDataEventStates.FailureSettingData -> {
                                setUiState(UiStates.Error)
                            }
                        }
                    }
                )
            }
        }
    }

    private suspend fun filterData() {
        viewModelScope.launch {
            val typeList = typesMap as List<TypeDataFirestore>
            val dishList = dishesMap as List<DishDataFirestore>

            val dataList = mutableListOf<MenuData>()
            for (type in typeList) {
                val dishesList = mutableListOf<DishDataFirestore>()
                for (dish in dishList) {
                    if (dish.typeId == type.id)
                        dishesList.add(dish)
                }
                dishesList.sortBy { dish: DishDataFirestore ->
                    dish.priority
                }
                dataList.add(MenuData(type, dishesList))
            }
            dataList.sortBy { menu: MenuData ->
                menu.typeData.priority
            }
            dataListState.value = dataList
        }.join()
    }
}