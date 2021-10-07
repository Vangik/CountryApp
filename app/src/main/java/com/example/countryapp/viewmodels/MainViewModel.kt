package com.example.countryapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apollographql.apollo.fetcher.ApolloResponseFetchers
import com.apollographql.apollo.rx3.rx
import com.example.countryapp.constants.Const
import com.example.countryapp.model.CountryModel
import com.example.countryapp.model.util.toCountryModel
import com.example.countryapp.repository.CountryRepository
import com.example.countryapp.repository.impl.CountryRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import com.example.countryapp.viewmodels.states.ViewState
import javax.inject.Inject


class MainViewModel @Inject constructor(private val repository: CountryRepositoryImpl) : ViewModel() {

    private val countryList = MutableLiveData<ViewState<List<CountryModel>>>()

    fun fetchCountryList() {
        countryList.postValue(ViewState.Loading())
        repository.getCountryList().subscribeOn(io()).observeOn(AndroidSchedulers.mainThread(), true)
            .subscribe({ response ->
                val list = response.data?.countries?.map { it.toCountryModel() }
                countryList.postValue(ViewState.Success(list))
            }, {
                countryList.postValue(ViewState.Error(Const.ERROR_MESSAGE))
            })
    }

    fun getCountryList() = countryList
}