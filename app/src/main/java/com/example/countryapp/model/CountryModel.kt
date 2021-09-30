package com.example.countryapp.model

import java.io.Serializable


data class CountryModel(
    val countryCode: String,
    val countryName: String,
    val countryImage: String,
    val countryCapital: String?,
    val countryRegion: String,
    val countryPopulation: String,
    val countryCurrencies: MutableList<String>?,
    val countryLanguageList: MutableList<CountryLanguage>,
    val countryCalling: MutableList<String>
) : Serializable

data class CountryLanguage(
        val language: String?
) : Serializable
