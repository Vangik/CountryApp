package com.example.countryapp.di

import com.apollographql.apollo.ApolloClient
import com.example.countryapp.model.DbImpl.CountryDbImpl
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

