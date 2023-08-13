package com.example.articlesproject.main.data

import com.example.articlesproject.main.domain.interfaces.StorageFirebaseFlowInterface
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageFirebaseResponseFlow @Inject constructor(): StorageFirebaseFlowInterface {

    private val dataFlow: MutableSharedFlow<StorageFirebase.StorageFirebaseData> =
        MutableSharedFlow(extraBufferCapacity = 1)

    override fun setItemInFlow(item: StorageFirebase.StorageFirebaseData) {
        dataFlow.tryEmit(item)
    }

    override fun getStorageFirebaseFlow(): MutableSharedFlow<StorageFirebase.StorageFirebaseData> {
        return dataFlow
    }


}