package com.example.articlesproject.main.domain.modules

import com.example.articlesproject.main.data.firestore.FirestoreGetGetDataListener
import com.example.articlesproject.main.data.firestore.FirestoreGetDataRepository
import com.example.articlesproject.main.data.storage.StorageFirebaseRepository
import com.example.articlesproject.main.data.firestore.FirestoreSetDataRepository
import com.example.articlesproject.main.data.firestore.interfaces.FirestoreGetDataInterface
import com.example.articlesproject.main.data.firestore.interfaces.FirestoreGetDataListenerInterface
import com.example.articlesproject.main.data.firestore.interfaces.FirestoreSetDataInterface
import com.example.articlesproject.main.data.storage.StorageFirebaseInterface
import com.example.articlesproject.main.domain.usecases.FirestoreGetDataUseCase
import com.example.articlesproject.main.domain.usecases.FirestoreSetDataUseCase
import com.example.articlesproject.main.domain.usecases.StorageFirebaseUseCase
import com.example.articlesproject.main.domain.usecases.interfaces.FirestoreGetDataUseCaseInterface
import com.example.articlesproject.main.domain.usecases.interfaces.FirestoreSetDataUseCaseInterface
import com.example.articlesproject.main.domain.usecases.interfaces.StorageFirebaseUseCaseInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MainModules {
    @Singleton
    @Binds
    fun bindFirestoreSetDataInterface(impl: FirestoreSetDataRepository): FirestoreSetDataInterface

    @Singleton
    @Binds
    fun bindFirestoreGetDataInterface(impl: FirestoreGetDataRepository): FirestoreGetDataInterface

    @Singleton
    @Binds
    fun bindFirestoreSetDataUseCaseInterface(impl: FirestoreSetDataUseCase): FirestoreSetDataUseCaseInterface

    @Singleton
    @Binds
    fun bindFirestoreGetDataUseCaseInterface(impl: FirestoreGetDataUseCase): FirestoreGetDataUseCaseInterface

    @Singleton
    @Binds
    fun bindStorageFirebaseInterface(impl: StorageFirebaseRepository): StorageFirebaseInterface

    @Singleton
    @Binds
    fun bindStorageFirebaseUseCaseInterface(impl: StorageFirebaseUseCase): StorageFirebaseUseCaseInterface

    @Singleton
    @Binds
    fun bindFirestoreGetDataListener(impl: FirestoreGetGetDataListener): FirestoreGetDataListenerInterface
}