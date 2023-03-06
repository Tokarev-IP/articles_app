package com.example.articlesproject.di

import com.example.articlesproject.data.AuthRepository
import com.example.articlesproject.domain.AuthInterface
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class Modules {

    @Provides
    fun provideNumber(): String = "355889"
}

@Module
interface InterfaceModule{
    @Binds
    fun bindAuthInterface(impl: AuthRepository): AuthInterface
}