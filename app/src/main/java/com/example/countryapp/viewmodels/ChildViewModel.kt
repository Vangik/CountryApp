package com.example.countryapp.viewmodels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apollographql.apollo.fetcher.ApolloResponseFetchers
import com.apollographql.apollo.rx3.rx
import com.example.countryapp.constants.Const
import com.example.countryapp.model.CountryModel
import com.example.countryapp.model.util.toCountryModel
import com.example.countryapp.repository.impl.CountryRepositoryImpl
import com.example.countryapp.viewmodels.states.ViewState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.*

class ChildViewModel(private val repository: CountryRepositoryImpl, private val s: String) :
    ViewModel() {

    private val countryDetails = MutableLiveData<ViewState<CountryModel>>()

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchCountryList() {
        countryDetails.postValue(ViewState.Loading())
        repository.getCountryById(s).responseFetcher(ApolloResponseFetchers.CACHE_FIRST).build()
            .rx().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val country = response.data?.country?.toCountryModel()
                Log.d("country", country.toString())

                val item = TimeZone.getAvailableIDs().map { id ->
                    val zone = ZoneId.of(id);
                    val offsetToday = OffsetDateTime.now(zone).offset

                    //val offset = offsetFormatter.format(offsetToday)    // GMT
                    val tokens = id.replace("_", " ").split("/")
                    val name = if (tokens.size == 2) {
                        val (country) = tokens
                        "country"
                    } else id
                   // Log.d("country", "$name, ${offsetToday}")
                    //Log.d("country", "$tokens, ")
                }
                val item2 = TimeZone.getTimeZone("America/Los_Angeles")
                Log.d("country", item2.toString())

                countryDetails.postValue(ViewState.Success(country))
            }, {
                countryDetails.postValue(ViewState.Error(Const.ERROR_MESSAGE))
            })
    }

    fun getCountryById() = countryDetails

}
