package com.example.articlesproject.login.domain.modules

import com.example.articlesproject.login.data.FirebaseAuthRepository
import com.example.articlesproject.login.data.GetAuthCodeRepository
import com.example.articlesproject.login.data.GetCredentialRepository
import com.example.articlesproject.login.data.interfaces.SignInCallbackInterface
import com.example.articlesproject.login.data.interfaces.FirebaseAuthInterface
import com.example.articlesproject.login.data.interfaces.GetAuthCodeInterface
import com.example.articlesproject.login.data.interfaces.SignInInterface
import com.example.articlesproject.login.domain.usecases.SignInCallback
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LoginModules {
    @Singleton
    @Binds
    fun bindSignInInterface(impl: GetCredentialRepository): SignInInterface

    @Singleton
    @Binds
    fun bindGetCodeInterface(impl: GetAuthCodeRepository): GetAuthCodeInterface

    @Singleton
    @Binds
    fun bindFirebaseAuthInterface(impl: FirebaseAuthRepository): FirebaseAuthInterface

    @Singleton
    @Binds
    fun bindSignInCallbackInterface(impl: SignInCallback): SignInCallbackInterface
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