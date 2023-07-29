package com.example.articlesproject.main.domain.usecases

import com.example.articlesproject.main.data.StorageFirebase
import com.example.articlesproject.main.data.StorageFirebaseResponseFlow
import com.example.articlesproject.main.domain.interfaces.StorageFirebaseFlowInterface
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageFirebaseFlowUseCase @Inject constructor(private val storageFirebaseFlowInterface: StorageFirebaseFlowInterface) {

    fun setItemInFlow(item: StorageFirebase.StorageFirebaseData) {
        storageFirebaseFlowInterface.setItemInFlow(item)
    }

    fun getStorageFirebaseFlow(): MutableSharedFlow<StorageFirebase.StorageFirebaseData> {
        return storageFirebaseFlowInterface.getStorageFirebaseFlow()
    }
}