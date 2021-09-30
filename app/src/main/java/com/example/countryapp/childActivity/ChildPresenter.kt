package com.example.countryapp.childActivity

import com.example.countryapp.constants.Const
import com.example.countryapp.model.CountryModel
import com.example.countryapp.model.util.toCountryModel
import com.example.countryapp.network.DbImpl.CountryDbImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io

class ChildPresenter(private val childView: ChildContract.View, val countryDbImpl: CountryDbImpl) :
    ChildContract.Presenter {

    private lateinit var countryDetails: CountryModel

    override fun fetchCountryDetails(id: String) {
           countryDbImpl.getCountryById(id).subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { response ->
                    countryDetails = response.data?.country!!.toCountryModel()
                    print(countryDbImpl)
                    childView.showCountryDetails(countryDetails)
                },{
                    childView.onError(Const.ERROR_MESSAGE)
                }
            )


    }


}