package com.example.countryapp.repository.impl

import com.apollographql.apollo.ApolloClient
import com.example.countryapp.CountryListQuery
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.fetcher.ApolloResponseFetchers
import com.apollographql.apollo.rx3.rx
import com.example.countryapp.CountryByIdQuery
import com.example.countryapp.repository.CountryRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(private val apolloClient: ApolloClient):CountryRepository {

    override fun getCountryList(): Observable<Response<CountryListQuery.Data>> {
        return apolloClient.query(CountryListQuery()).toBuilder().responseFetcher(ApolloResponseFetchers.CACHE_FIRST).build().rx()
    }

    override fun getCountryById(id: String): Observable<Response<CountryByIdQuery.Data>> {
        return apolloClient.query(CountryByIdQuery(id)).toBuilder().responseFetcher(ApolloResponseFetchers.CACHE_FIRST).build().rx()
    }

    companion object {
        const val URL = "https://countries.trevorblades.com"
    }
}
