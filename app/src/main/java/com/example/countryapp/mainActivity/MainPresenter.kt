package com.example.countryapp.mainActivity

import com.example.countryapp.model.CountryModel
import com.example.countryapp.model.util.DataMapper
import com.example.countryapp.network.DbImpl.CountryDbImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter (private val mainActivity: MainContract.View, private val countryQuery: CountryDbImpl) :
    MainContract.CountryPresenter {

    private val countryList: MutableList<CountryModel> = mutableListOf()

    override fun getCountryList() {
        val dataMapper = DataMapper()
        mainActivity.showProgressBar()
        countryQuery.getCountryList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.data?.countries?.forEach { country -> countryList.add(dataMapper.mapFromEntityToModel(country)) }
                mainActivity.showCountryList(countryList)
            }, {
                mainActivity.onError()
            }, {
                mainActivity.hideProgressBar()
            })
    }
}