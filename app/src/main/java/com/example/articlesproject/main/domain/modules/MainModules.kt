package com.example.articlesproject.main.domain.modules

import com.example.articlesproject.main.data.StorageFirebase
import com.example.articlesproject.main.data.data.firestore.FirestoreSetData
import com.example.articlesproject.main.data.data.firestore.interfaces.FirestoreSetDataInterface
import com.example.articlesproject.main.data.data.firestore.interfaces.StorageFirebaseInterface
import com.example.articlesproject.main.domain.usecases.FirestoreSetDataUseCase
import com.example.articlesproject.main.domain.usecases.interfaces.FirestoreSetDataUseCaseInterface
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
    fun bindFirestoreSetDataInterface(impl: FirestoreSetData): FirestoreSetDataInterface

    @Singleton
    @Binds
    fun bindFirestoreSetDataUseCaseInterface(impl: FirestoreSetDataUseCase): FirestoreSetDataUseCaseInterface

    @Singleton
    @Binds
    fun bindStorageFirebaseInterface(impl: StorageFirebase): StorageFirebaseInterface
}