package com.example.countryapp.application

import android.app.Application
import com.example.countryapp.di.components.AppComponent
import com.example.countryapp.di.components.DaggerAppComponent

class CountryApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder().build()
    }
}