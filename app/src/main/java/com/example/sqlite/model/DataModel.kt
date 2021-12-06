package com.example.sqlite.model

data class DataModel(
    val countryId: Int = 0,
    val name: String,
    val capital: String?,
    val region: String?,
    val native: String?,
    val currency: String?,
    val language: String?
)