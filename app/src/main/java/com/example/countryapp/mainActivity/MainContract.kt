package com.example.countryapp.mainActivity

import com.example.countryapp.base.BasePresenter
import com.example.countryapp.model.CountryModel

interface MainContract {
    interface View  {
        fun showProgressBar()
        fun hideProgressBar()
        fun onError()
        fun showCountryList(list: MutableList<CountryModel>)
    }

    interface CountryPresenter {
        fun getCountryList()
    }
}