package com.example.countryapp.di.modules

import com.apollographql.apollo.ApolloClient
import com.example.countryapp.network.DbImpl.CountryDbImpl
import dagger.Module
import dagger.Provides


@Module
class GraphQlModule {

    @Provides
    fun provideGraphQlRepository(): ApolloClient =
        ApolloClient.builder().serverUrl(provideUrl()).build()

    @Provides
    fun provideUrl() = CountryDbImpl.URL
}

