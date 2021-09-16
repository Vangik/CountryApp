package com.example.countryapp.model

import com.example.countryapp.CountryListQuery
import java.io.Serializable
import java.io.StringReader


data class CountryModel
    (
    var countryName: String,
    var countryImage: String,
    var countryCapital: String?,
    var countryRegion: String,
    var countryPopulation: String,
    var countryCurrencies: String?,
    var countryLanguageList: MutableList<CountryLanguage>,
    var countryCalling: String
) : Serializable

data class CountryLanguage(
    var language: String?
):Serializable
