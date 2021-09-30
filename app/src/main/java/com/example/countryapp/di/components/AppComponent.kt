package com.example.countryapp.di.components

import com.example.countryapp.childActivity.ChildActivity
import com.example.countryapp.di.modules.GraphQlModule
import com.example.countryapp.mainActivity.MainActivity
import dagger.Component


@Component(modules = [GraphQlModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivity: ChildActivity)
}

