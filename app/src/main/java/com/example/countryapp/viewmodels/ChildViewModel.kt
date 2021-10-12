package com.example.countryapp.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryapp.constants.Const
import com.example.countryapp.model.CountryModel
import com.example.countryapp.model.util.toCountryModel
import com.example.countryapp.repository.CountryRepository
import com.example.countryapp.repository.impl.CountryRepositoryImpl
import com.example.countryapp.viewmodels.states.ViewState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ChildViewModel (private var repository: CountryRepository) :
    ViewModel() {

    private val countryDetails = MutableLiveData<ViewState<CountryModel>>()

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchCountryList(s: String) {
        countryDetails.postValue(ViewState.Loading())
        repository.getCountryById(s).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val country = response.data?.country?.toCountryModel()
                countryDetails.postValue(ViewState.Success(country))
            }, {
                countryDetails.postValue(ViewState.Error(Const.ERROR_MESSAGE))
            })
    }

    fun getCountry() = countryDetails

    fun setRepository(repository: CountryRepository){
        this.repository = repository
    }
}
