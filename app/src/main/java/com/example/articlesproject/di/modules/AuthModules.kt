package com.example.articlesproject.di

import com.example.articlesproject.data.AuthCallback
import com.example.articlesproject.data.SendCodeRepository
import com.example.articlesproject.domain.AuthInterface
import com.example.articlesproject.domain.ResponseInterface
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface AuthModule{
    @AppScope
    @Binds
    fun bindAuthInterface(impl: SendCodeRepository): AuthInterface

    @AppScope
    @Binds
    fun bindResponseInterface(impl: AuthCallback): ResponseInterface
}

@Module
object FirebaseModule{
    @AppScope
    @Provides
    fun provideAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}