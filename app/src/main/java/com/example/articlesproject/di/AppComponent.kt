package com.example.articlesproject.di

import android.content.Context
import com.example.articlesproject.App
import dagger.BindsInstance
import dagger.Component

@Component(modules = [Modules::class, InterfaceModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(app: App)
}