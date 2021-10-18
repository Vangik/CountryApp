package com.example.countryapp.mainActivity

import android.view.View
import android.widget.SearchView
import android.widget.Toast
import com.example.countryapp.viewmodels.states.ViewState
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.countryapp.ViewBindingActivity
import com.example.countryapp.application.CountryApplication
import com.example.countryapp.constants.Const
import com.example.countryapp.mainActivity.adapter.CountryAdapter
import com.example.countryapp.model.CountryModel
import com.example.countryapp.viewmodels.MainViewModel
import com.example.countryapp.viewmodels.ViewModelFactory
import javax.inject.Inject
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import com.example.countryapp.databinding.ActivityMainBinding


class MainActivity : ViewBindingActivity<ActivityMainBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(
            this@MainActivity,
            viewModelFactory
        )[MainViewModel::class.java]
    }

    override fun setup(): Unit = with(binding) {
        (application as CountryApplication).appComponent.inject(this@MainActivity)
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
                else -> Toast.makeText(this, Const.ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setRecyclerView(languageList: List<CountryModel>) {
        val countryAdapter = CountryAdapter(languageList, this@MainActivity)
        binding.rvMainActivityCountryDetails.adapter = countryAdapter
        countryAdapter.submitList(languageList)
        binding.countrySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                countryAdapter.filter.filter(newText)
                return false
            }
        })
    }
}