package com.example.countryapp.network.DbImpl

import com.apollographql.apollo.ApolloClient
import com.example.countryapp.CountryListQuery
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx3.rxQuery
import com.example.countryapp.network.CountryDb
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class CountryDbImpl @Inject constructor(private val apolloClient: ApolloClient) : CountryDb {

    override fun getCountryList(): Observable<Response<CountryListQuery.Data>> {
        return apolloClient.rxQuery(CountryListQuery())
    }

    companion object {
        const val URL = "https://countries.trevorblades.com"
    }

}
