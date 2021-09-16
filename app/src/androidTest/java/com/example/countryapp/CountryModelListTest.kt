package com.example.countryapp

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx3.rxQuery
import com.example.countryapp.model.CountryModelList
import com.example.countryapp.model.CountryModelList.Companion.getCountryList
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test


internal class CountryModelListTest{
    @Test
    fun testConnection(){

        lateinit var countryList:List<CountryListQuery.Country>
        val countries = CountryModelList
        countries.getCountryList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.data?.let { println( it.countries.toString())
                }
            },{

            })
        Log.d("Log hello","")
    }
}