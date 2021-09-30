package com.example.countryapp.di.modules

import com.example.countryapp.mainActivity.MainActivity
import com.example.countryapp.mainActivity.MainPresenter
import com.example.countryapp.network.DbImpl.CountryDbImpl
import dagger.Module
import dagger.Provides

@Module
class MainPresenterModule {

    @Provides
    fun provideMainPresenter(mainActivity: MainActivity, countryDbImpl: CountryDbImpl): MainPresenter =
        MainPresenter(mainActivity, countryDbImpl)

    @Provides
    fun provideMainActivity() = MainActivity ()

}