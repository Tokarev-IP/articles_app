package com.example.articlesproject

import android.app.Activity
import android.app.Application
import com.example.articlesproject.di.AppComponent
import com.example.articlesproject.di.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}

fun Activity.getComponent(): AppComponent = (application as App).appComponent