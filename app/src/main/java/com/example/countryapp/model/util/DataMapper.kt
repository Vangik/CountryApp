package com.example.countryapp.model.util

import com.example.countryapp.CountryByIdQuery
import com.example.countryapp.CountryListQuery
import com.example.countryapp.constants.Const
import com.example.countryapp.model.CountryLanguage
import com.example.countryapp.model.CountryModel


fun CountryListQuery.Country.toCountryModel() = CountryModel(
    code,
    name,
    emoji,
    capital.toCapital(),
    continent.name,
    native_,
    currency?.toCurrencies(),
    languages.toLanguageList(),
    phone.toPhones()
)

fun String.toPhones() = this.split(Const.SPLIT_STRING_DELIMITER).toMutableList()
fun String?.toCapital() = this ?: Const.CAPITAL_ERROR
fun String.toCurrencies() = this.split(Const.SPLIT_STRING_DELIMITER).toMutableList()

fun List<CountryListQuery.Language>.toLanguageList(): MutableList<CountryLanguage> {
    val countryLanguageList = mutableListOf<CountryLanguage>()
    this.forEach { countryLanguageList.add(CountryLanguage(it.name)) }
    return countryLanguageList
}

fun CountryByIdQuery.Country.toCountryModel() = CountryModel(
    code,
    name,
    emoji,
    capital.toCapital(),
    continent.name,
    native_,
    currency?.toCurrencies(),
    languages.toLanguageList2(),
    phone.toPhones()
)

fun List<CountryByIdQuery.Language>.toLanguageList2(): MutableList<CountryLanguage> {
    val countryLanguageList = mutableListOf<CountryLanguage>()
    this.forEach { countryLanguageList.add(CountryLanguage(it.name)) }
    return countryLanguageList
}



