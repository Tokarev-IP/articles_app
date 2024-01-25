package com.example.articlesproject.main.domain.usecases

import com.example.articlesproject.main.data.storage.StorageFirebaseInterface
import com.example.articlesproject.main.domain.usecases.interfaces.StorageFirebaseUseCaseInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageFirebaseUseCase @Inject constructor(
    private val storageFirebaseInterface: StorageFirebaseInterface
): StorageFirebaseUseCaseInterface {

}