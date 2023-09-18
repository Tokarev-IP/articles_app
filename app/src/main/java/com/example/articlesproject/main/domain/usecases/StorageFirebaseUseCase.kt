package com.example.articlesproject.main.domain.usecases

import android.net.Uri
import com.example.articlesproject.main.data.data.MenuData
import com.example.articlesproject.main.domain.UiResults
import com.example.articlesproject.main.domain.interfaces.StorageFirebaseInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageFirebaseUseCase @Inject constructor(private val storageFirebaseInterface: StorageFirebaseInterface) {

    suspend fun uploadFileFirebase(
        menuData: List<MenuData>,
        onResult:(UiResults) -> Unit,
    ) {
        for (i in menuData) {
            for (dish in i.dishesList) {
                dish.pictureUri?.let {uri ->
                    storageFirebaseInterface.uploadFileFirebase(
                        uri,
                        onSuccessful = {},
                        onError = { it -> onResult(UiResults.Error(it)) },
                    )
                }
            }
        }
        onResult(UiResults.Successful)
    }

    fun downloadFileFirebase(fullUrl: String) {
        storageFirebaseInterface.downloadFileFirebase(fullUrl)
    }
}