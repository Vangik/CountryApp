package com.example.countryapp.countryRepository.impl

import com.apollographql.apollo.ApolloClient
import com.example.countryapp.CountryListQuery
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx3.rxQuery
import com.example.countryapp.CountryByIdQuery
import com.example.countryapp.countryRepository.CountryRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(private val apolloClient: ApolloClient) : CountryRepository {

    override fun getCountryList(): Observable<Response<CountryListQuery.Data>> {
        return apolloClient.rxQuery(CountryListQuery())
    }

    override fun getCountryById(id: String): Observable<Response<CountryByIdQuery.Data>> {
        return apolloClient.rxQuery(CountryByIdQuery(id))
    }


    companion object {
        const val URL = "https://countries.trevorblades.com"
    }
}
