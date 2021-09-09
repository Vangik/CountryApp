package com.example.countryapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class CountryModel (
    val countryName: String,
    val countryImage: Int,
    val countryCapital: String,
    val countryRegion: String,
    val countryPopulation : String,
    val countryCurrencies : String,
    val countryLanguage: String,
    val countryTimeZone: String,
    val countryCalling: String
) : Parcelable

