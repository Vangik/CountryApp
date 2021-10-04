package com.example.countryapp.childActivity

import com.example.countryapp.constants.Const
import com.example.countryapp.model.CountryModel
import com.example.countryapp.model.util.toCountryModel
import com.example.countryapp.countryRepository.impl.CountryRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io

class ChildPresenter(private val childView: ChildContract.View, val countryRepositoryImpl: CountryRepositoryImpl) :
    ChildContract.Presenter {

    private lateinit var countryDetails: CountryModel

    override fun fetchCountryDetails(id: String) {
           countryRepositoryImpl.getCountryById(id).subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { response ->
                    countryDetails = response.data?.country!!.toCountryModel()
                    print(countryRepositoryImpl)
                    childView.showCountryDetails(countryDetails)
                },{
                    childView.onError(Const.ERROR_MESSAGE)
                }
            )


    }


}