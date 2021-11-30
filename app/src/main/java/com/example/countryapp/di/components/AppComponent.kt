package com.example.countryapp.di.components

import com.example.countryapp.di.modules.GraphQlModule
import com.example.countryapp.di.modules.ViewModelFactoryModule
import com.example.countryapp.fragments.ChildFragment
import com.example.countryapp.fragments.MainFragment
import com.example.countryapp.mainActivity.MainActivity
import dagger.Component
import dagger.Subcomponent

@Component(modules = [GraphQlModule::class, ViewModelFactoryModule::class])
interface AppComponent{

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(childFragment: ChildFragment)
}

