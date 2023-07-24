package com.example.articlesproject.main.domain.interfaces

import com.example.articlesproject.main.data.StorageFirebase
import kotlinx.coroutines.flow.MutableSharedFlow

interface StorageFirebaseFlowInterface {

    fun setItemInFlow(item: StorageFirebase.StorageFirebaseData)

    fun getStorageFirebaseFlow(): MutableSharedFlow<StorageFirebase.StorageFirebaseData>
}