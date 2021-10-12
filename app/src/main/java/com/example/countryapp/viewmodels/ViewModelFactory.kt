package com.example.countryapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countryapp.repository.impl.CountryRepositoryImpl
import java.lang.IllegalArgumentException
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val repository: CountryRepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(this.repository) as T
            }
            modelClass.isAssignableFrom(ChildViewModel::class.java) -> {
                ChildViewModel(this.repository) as T
            }
            else -> throw IllegalArgumentException("ViewModel not found")
        }
    }
}