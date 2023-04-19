package com.example.articlesproject.login.modules

import com.example.articlesproject.login.data.AuthRepository
import com.example.articlesproject.login.data.GetCodeRepository
import com.example.articlesproject.login.domain.interfaces.GetCodeInterface
import com.example.articlesproject.login.domain.interfaces.SignInInterface
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {
    @Singleton
    @Binds
    fun bindSignInInterface(impl: AuthRepository): SignInInterface

    @Singleton
    @Binds
    fun bindGetCodeInterface(impl: GetCodeRepository): GetCodeInterface
}

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Singleton
    @Provides
    fun provideAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}