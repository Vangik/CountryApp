package com.example.countryapp.mainActivity

import com.example.countryapp.model.CountryModel
import com.example.countryapp.model.util.toCountryModel
import com.example.countryapp.countryRepository.impl.CountryRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainPresenter (private val mainView: MainContract.View, private val countryQuery: CountryRepositoryImpl) :
    MainContract.CountryPresenter {

    private var countryList: MutableList<CountryModel> = mutableListOf()

    override fun getCountryList() {
        mainView.showProgressBar()
        countryQuery.getCountryList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                countryList = response.data?.countries?.map { it.toCountryModel() } as MutableList<CountryModel>
                mainView.showCountryList(countryList)
            }, {
                mainView.onError()
            }, {
                mainView.hideProgressBar()
            })
    }
}