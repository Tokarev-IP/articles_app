package com.example.articlesproject.main.presentation.viewmodel

import android.util.Log
import com.example.articlesproject.main.data.firestore.data.DataIdFirestore
import com.example.articlesproject.main.data.firestore.data.DishDataFirestore
import com.example.articlesproject.main.data.firestore.data.MenuDataFirestore
import com.example.articlesproject.main.data.firestore.data.TypeDataFirestore
import com.example.articlesproject.main.domain.usecases.interfaces.FirestoreGetDataUseCaseInterface
import com.example.articlesproject.main.presentation.states.GetDataEventStates
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDataMenu @Inject constructor(
    private val firestoreGetDataUseCaseInterface: FirestoreGetDataUseCaseInterface,
) {
    fun getMenuId(
        onDataEvent: (dataEvent: GetDataEventStates) -> Unit
    ) {
        Log.d("DAVAI", "Get Menu ID fun")
        firestoreGetDataUseCaseInterface.getMenuIdDocument(
            onSuccess = { dataId: DataIdFirestore ->
                onDataEvent(GetDataEventStates.CompleteGettingDocumentData(dataId))
            },
            onNullDocument = {
                onDataEvent(GetDataEventStates.NullGettingData)
            },
            onFailure = { e: String ->
                onDataEvent(GetDataEventStates.FailureGettingData(e))
            }
        )
    }

    fun getTypeData(
        dataId: String,
        onDataEvent: (dataEvent: GetDataEventStates) -> Unit
    ) {
        firestoreGetDataUseCaseInterface.getTypeListData(
            dataId = dataId,
            onSuccess = { dataList: List<TypeDataFirestore> ->
                onDataEvent(GetDataEventStates.CompleteGettingListData(dataList))
            },
            onNullDocument = {
                onDataEvent(GetDataEventStates.NullGettingData)
            },
            onFailure = { e: String ->
                onDataEvent(GetDataEventStates.FailureGettingData(e))
            }
        )
    }

    fun getDishData(
        dataId: String,
        onDataEvent: (dataEvent: GetDataEventStates) -> Unit
    ) {
        firestoreGetDataUseCaseInterface.getDishListData(
            dataId = dataId,
            onSuccess = {dataList: List<DishDataFirestore> ->
                onDataEvent(GetDataEventStates.CompleteGettingListData(dataList))
            },
            onNullDocument = {
                onDataEvent(GetDataEventStates.NullGettingData)
            },
            onFailure = { e: String ->
                onDataEvent(GetDataEventStates.FailureGettingData(e))
            }
        )
    }

    fun getMenuData(
        dataId: String,
        onDataEvent: (dataEvent: GetDataEventStates) -> Unit
    ) {
        firestoreGetDataUseCaseInterface.getMenuData(
            dataId = dataId,
            onSuccess = { menuDataList: List<MenuDataFirestore> ->
                onDataEvent(GetDataEventStates.CompleteGettingListData(menuDataList))
            },
            onNullDocument = {
                onDataEvent(GetDataEventStates.NullGettingData)
            },
            onFailure = { e: String ->
                onDataEvent(GetDataEventStates.FailureGettingData(e))
            }
        )
    }
}