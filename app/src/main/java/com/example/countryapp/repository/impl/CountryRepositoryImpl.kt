package com.example.countryapp.repository.impl

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.example.countryapp.CountryListQuery
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.fetcher.ApolloResponseFetchers
import com.apollographql.apollo.rx3.rxQuery
import com.example.countryapp.CountryByIdQuery
import com.example.countryapp.repository.CountryRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(val apolloClient: ApolloClient):CountryRepository {

    override fun getCountryList(): ApolloQueryCall.Builder<CountryListQuery.Data> {
        return apolloClient.query(CountryListQuery()).toBuilder()
    }

    override fun getCountryById(id: String): ApolloQueryCall.Builder<CountryByIdQuery.Data> {
        return apolloClient.query(CountryByIdQuery(id)).toBuilder()
    }

    fun fetchCountryList() = apolloClient.apolloStore.read(CountryListQuery()).execute()

    fun fetchCountryDetails(s:String) = apolloClient.apolloStore.read(CountryByIdQuery(s)).execute()

    private lateinit var list: CountryListQuery.Data
    fun getData() = apolloClient.apolloStore.writeAndPublish(CountryListQuery(),list)

    companion object {
        const val URL = "https://countries.trevorblades.com"
    }
}
