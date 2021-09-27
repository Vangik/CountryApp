package com.example.countryapp.di.modules

import com.apollographql.apollo.ApolloClient
import com.example.countryapp.mainActivity.MainActivity
import com.example.countryapp.mainActivity.MainContract
import com.example.countryapp.mainActivity.MainPresenter
import com.example.countryapp.network.DbImpl.CountryDbImpl
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
class MainPresenterModule {

    @Provides
    fun provideMainPresenter(mainActivity: MainActivity, countryDbImpl: CountryDbImpl): MainPresenter =
        MainPresenter(mainActivity, countryDbImpl)

}