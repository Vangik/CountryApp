package com.example.countryapp.di.components

import com.example.countryapp.di.modules.GraphQlModule
import com.example.countryapp.di.modules.MainPresenterModule
import com.example.countryapp.mainActivity.MainActivity
import com.example.countryapp.mainActivity.MainPresenter
import dagger.Component
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

@Component(modules = [GraphQlModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}

