package com.example.countryapp.base

interface BasePresenter<T> {
    fun setView(view: T)
}