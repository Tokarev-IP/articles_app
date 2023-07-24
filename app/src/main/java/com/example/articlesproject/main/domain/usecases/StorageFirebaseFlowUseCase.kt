package com.example.articlesproject.main.domain.usecases

import com.example.articlesproject.main.data.StorageFirebase
import com.example.articlesproject.main.data.StorageFirebaseResponseFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageFirebaseFlowUseCase @Inject constructor(private val storageFirebaseResponseFlow: StorageFirebaseResponseFlow) {

    fun setItemInFlow(item: StorageFirebase.StorageFirebaseData) {
        storageFirebaseResponseFlow.setItemInFlow(item)
    }

    fun getStorageFirebaseFlow(): MutableSharedFlow<StorageFirebase.StorageFirebaseData> {
        return storageFirebaseResponseFlow.getStorageFirebaseFlow()
    }
}