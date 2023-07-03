package com.example.articlesproject.login.domain.modules

import com.example.articlesproject.login.data.AuthDataFlow
import com.example.articlesproject.login.data.FirebaseAuthRepository
import com.example.articlesproject.login.data.SignInRepository
import com.example.articlesproject.login.data.GetAuthCodeRepository
import com.example.articlesproject.login.domain.interfaces.AuthDataFlowInterface
import com.example.articlesproject.login.domain.interfaces.FirebaseAuthInterface
import com.example.articlesproject.login.domain.interfaces.GetAuthCodeInterface
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
    fun bindSignInInterface(impl: SignInRepository): SignInInterface

    @Singleton
    @Binds
    fun bindGetCodeInterface(impl: GetAuthCodeRepository): GetAuthCodeInterface

    @Singleton
    @Binds
    fun bindAuthDataFlowInterface(impl: AuthDataFlow): AuthDataFlowInterface

    @Singleton
    @Binds
    fun bindFirebaseAuthInterface(impl: FirebaseAuthRepository): FirebaseAuthInterface
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