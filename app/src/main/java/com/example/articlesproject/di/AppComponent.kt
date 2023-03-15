package com.example.articlesproject.di

import android.content.Context
import com.example.articlesproject.App
import com.example.articlesproject.MainActivity
import com.example.articlesproject.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [FirebaseModule::class, AuthModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(app: App)
    fun getMainActivityComponent(): MainActivitySubcomponent.Factory
    fun inject(activity: MainActivity)
}