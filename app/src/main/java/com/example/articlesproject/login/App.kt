package com.example.articlesproject.login

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

//    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

//        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}

//fun Activity.getComponent(): AppComponent = (application as App).appComponent