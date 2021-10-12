package com.example.countryapp

import androidx.lifecycle.MutableLiveData
import com.example.countryapp.model.CountryModel
import com.example.countryapp.repository.impl.CountryRepositoryImpl
import com.example.countryapp.viewmodels.MainViewModel
import com.example.countryapp.viewmodels.states.ViewState
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MainViewModelTest {

    @Mock
    lateinit var repository: CountryRepositoryImpl
    lateinit var viewModel: MainViewModel

    private val countryList = MutableLiveData<ViewState<List<CountryModel>>>()

    @Before
    fun setup(){
        viewModel = MainViewModel(repository)
    }

    @Test
    fun fetchCountryList() {

    }
}