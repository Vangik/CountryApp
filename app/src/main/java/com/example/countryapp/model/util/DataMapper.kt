package com.example.countryapp.model.util

import com.example.countryapp.CountryListQuery
import com.example.countryapp.constants.CountryConst
import com.example.countryapp.model.CountryLanguage
import com.example.countryapp.model.CountryModel

class DataMapper : EntityMapper<CountryListQuery.Country, CountryModel> {

    override fun mapFromEntityToModel(entity: CountryListQuery.Country): CountryModel {
        val countryLanguageList: MutableList<CountryLanguage> = mutableListOf()
        return with(entity) {
            CountryModel(
                name,
                emoji,
                capital ?: CountryConst.CAPITAL_ERROR,
                continent.name,
                native_,
                currency?.split(" ")?.toMutableList(),
                countryLanguageList = countryLanguageList.apply {
                    languages.forEach { language ->
                        add(CountryLanguage(language.name))
                    }
                },
                phone.split(",").toMutableList()
            )
        }
    }
}