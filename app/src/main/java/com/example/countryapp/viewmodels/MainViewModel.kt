package com.example.countryapp.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryapp.constants.Const
import com.example.countryapp.model.CountryModel
import com.example.countryapp.model.util.toCountryModel
import com.example.countryapp.repository.CountryRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import com.example.countryapp.viewmodels.states.ViewState
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class MainViewModel @Inject constructor(private var repository: CountryRepository) : ViewModel() {

    private var countryList = MutableLiveData<ViewState<List<CountryModel>>>()

    fun fetchCountryList() {
        countryList.postValue(ViewState.Loading())
        repository.getCountryList().subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val list = response.data?.countries?.map { it.toCountryModel() }
                countryList.postValue(ViewState.Success(list))
            }, {
                countryList.postValue(ViewState.Error(Const.ERROR_MESSAGE))
            })
    }

    private val countryDetails = MutableLiveData<ViewState<CountryModel>>()

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchCountryList(s: String?) {
        countryDetails.postValue(ViewState.Loading())
        s?.let {
            repository.getCountryById(it).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    val country = response.data?.country?.toCountryModel()
                    countryDetails.postValue(ViewState.Success(country))
                }, {
                    countryDetails.postValue(ViewState.Error(Const.ERROR_MESSAGE))
                })
        }
    }

    fun getCountry() = countryDetails

    fun getCountryList() = countryList

}