package com.example.countryapp.mainActivity

import android.view.View
import android.widget.Toast
import com.example.countryapp.viewmodels.states.ViewState
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.countryapp.ViewBindingActivity
import com.example.countryapp.application.CountryApplication
import com.example.countryapp.constants.Const
import com.example.countryapp.mainActivity.adapter.CountryAdapter
import com.example.countryapp.databinding.ActivityMainBinding
import com.example.countryapp.model.CountryModel
import com.example.countryapp.repository.impl.CountryRepositoryImpl
import com.example.countryapp.viewmodels.MainViewModel
import com.example.countryapp.viewmodels.ViewModelFactory
import javax.inject.Inject


class MainActivity : ViewBindingActivity<ActivityMainBinding>() {

    @Inject
    lateinit var countryQuery: CountryRepositoryImpl
    private lateinit var mainViewModel: MainViewModel

    override fun setup(): Unit = with(binding) {
        (application as CountryApplication).appComponent.inject(this@MainActivity)
        mainViewModel = ViewModelProvider(
            this@MainActivity,
            ViewModelFactory(countryQuery)
        ).get(MainViewModel::class.java)
        mainViewModel.fetchCountryList()
        observeLiveData()
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    private fun observeLiveData() {
        mainViewModel.getCountryList().observe(this@MainActivity, Observer { response ->
            when (response) {
                is ViewState.Loading -> {
                    binding.pbMainActivity.visibility = View.VISIBLE
                }
                is ViewState.Success -> {
                    binding.pbMainActivity.visibility = View.GONE
                    response.value?.let { setRecyclerView(it) }
                }
                is ViewState.Error -> {
                    Toast.makeText(this, Const.ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setRecyclerView(languageList: List<CountryModel>) {
        val countryAdapter = CountryAdapter(languageList, this@MainActivity)
        binding.rvMainActivityCountryDetails.adapter = countryAdapter
        countryAdapter.submitList(languageList)
    }
}