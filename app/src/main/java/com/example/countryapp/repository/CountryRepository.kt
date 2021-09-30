package com.example.countryapp.repository

import com.apollographql.apollo.ApolloQueryCall
import com.example.countryapp.CountryByIdQuery
import com.example.countryapp.CountryListQuery

interface CountryRepository {
    fun getCountryList(): ApolloQueryCall.Builder<CountryListQuery.Data>
    fun getCountryById(id: String): ApolloQueryCall.Builder<CountryByIdQuery.Data>

}