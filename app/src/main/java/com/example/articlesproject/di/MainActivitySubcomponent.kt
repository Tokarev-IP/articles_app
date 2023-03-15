package com.example.articlesproject.di

import com.example.articlesproject.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@MainActivityScope
@Subcomponent(modules = [])
interface MainActivitySubcomponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(@BindsInstance activity: MainActivity): MainActivitySubcomponent
    }

    fun inject(activity: MainActivity)
}