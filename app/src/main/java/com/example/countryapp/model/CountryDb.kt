package com.example.countryapp.model

import com.apollographql.apollo.api.Response
import com.example.countryapp.CountryListQuery
import io.reactivex.rxjava3.core.Observable

interface CountryDb {
    fun getCountryList(): Observable<Response<CountryListQuery.Data>>
}