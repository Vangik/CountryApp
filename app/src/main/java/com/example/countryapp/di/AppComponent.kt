package com.example.countryapp.di

import com.example.countryapp.application.CountryApplication
import com.example.countryapp.mainActivity.MainActivity
import dagger.Component


@Component(modules = [GraphQlModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}