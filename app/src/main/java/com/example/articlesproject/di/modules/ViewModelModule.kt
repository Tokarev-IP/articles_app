package com.example.articlesproject.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.articlesproject.di.AppScope
import com.example.articlesproject.di.ViewModelFactory
import com.example.articlesproject.di.ViewModelKey
import com.example.articlesproject.presentation.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @AppScope
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @AppScope
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun splashViewModel(viewModel: AuthViewModel): ViewModel
}