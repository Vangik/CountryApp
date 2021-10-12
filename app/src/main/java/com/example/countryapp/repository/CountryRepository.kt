package com.example.countryapp.repository

import com.apollographql.apollo.api.Response
import com.example.countryapp.CountryByIdQuery
import com.example.countryapp.CountryListQuery
import io.reactivex.rxjava3.core.Observable

interface CountryRepository {
    fun getCountryList(): Observable<Response<CountryListQuery.Data>>
    fun getCountryById(id: String): Observable<Response<CountryByIdQuery.Data>>

}