package com.example.articlesproject.main.presentation.viewmodel

import com.example.articlesproject.main.data.firestore.data.DishDataFirestore
import com.example.articlesproject.main.data.firestore.data.MenuDataFirestore
import com.example.articlesproject.main.data.firestore.data.TypeDataFirestore
import com.example.articlesproject.main.domain.usecases.interfaces.FirestoreSetDataUseCaseInterface
import com.example.articlesproject.main.presentation.states.SetDataEventStates
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetDataMenu @Inject constructor(
    private val firestoreSetDataUseCaseInterface: FirestoreSetDataUseCaseInterface,
) {
    fun setTypeData(
        typeData: TypeDataFirestore,
        dataId: String,
        onEventState: (dataEventStates: SetDataEventStates) -> Unit,
    ) {
        firestoreSetDataUseCaseInterface.setTypeData(
            typeData = typeData,
            onComplete = { msg: String ->
                onEventState(SetDataEventStates.CompleteSettingData(msg))
            },
            onFailure = { e: String ->
                onEventState(SetDataEventStates.FailureSettingData(e))
            },
            dataId = dataId,
        )
    }

    fun setDishData(
        dishData: DishDataFirestore,
        dataId: String,
        onEventState: (dataEventStates: SetDataEventStates) -> Unit,
    ) {
        firestoreSetDataUseCaseInterface.setDishData(
            dishData = dishData,
            onComplete = { msg: String ->
                onEventState(SetDataEventStates.CompleteSettingData(msg))
            },
            onFailure = { e: String ->
                onEventState(SetDataEventStates.FailureSettingData(e))
            },
            dataId = dataId,
        )
    }

    fun setMenuData(
        menuData: MenuDataFirestore,
        dataId: String,
        onEventState: (dataEventStates: SetDataEventStates) -> Unit,
    ){
        firestoreSetDataUseCaseInterface.setMenuData(
            menuData = menuData,
            onComplete = { msg: String ->
                onEventState(SetDataEventStates.CompleteSettingData(msg))
            },
            onFailure = { e: String ->
                onEventState(SetDataEventStates.FailureSettingData(e))
            },
            dataId = dataId,
        )
    }

    fun setDataId(
        onReturnDataId:(dataId: String) -> Unit,
        onEventState: (dataEventStates: SetDataEventStates) -> Unit,
    ) {
        val newId = UUID.randomUUID().toString()
        firestoreSetDataUseCaseInterface.setDataId(
            dataId = newId,
            onComplete = { msg: String ->
                onEventState(SetDataEventStates.CompleteSettingData(msg))
                onReturnDataId(newId)
            },
            onFailure = { e: String ->
                onEventState(SetDataEventStates.FailureSettingData(e))
            },
        )
    }

}