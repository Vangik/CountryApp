package com.example.countryapp.model

import com.apollographql.apollo.ApolloClient
import com.example.countryapp.CountryListQuery
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx3.rxQuery
import io.reactivex.rxjava3.core.Observable

class CountryDbImpl(
        private var apolloClient: ApolloClient = ApolloClient.builder()
                .serverUrl(URL).build()
) : CountryDb {

    override fun getCountryList(): Observable<Response<CountryListQuery.Data>> {
        return apolloClient.rxQuery(CountryListQuery())
    }

    companion object {
        const val URL = "https://countries.trevorblades.com"
    }

}
