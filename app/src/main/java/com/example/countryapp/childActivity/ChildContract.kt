package com.example.countryapp.childActivity

import com.example.countryapp.model.CountryModel

interface ChildContract {
    interface View {
        fun onError(s: String)
        fun showCountryDetails(countryDetails: CountryModel)
    }

    interface Presenter {
        fun fetchCountryDetails(id: String)
    }
}