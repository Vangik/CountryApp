package com.example.countryapp.childActivity

import android.annotation.SuppressLint
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.countryapp.R
import com.example.countryapp.childActivity.adapter.LanguageAdapter
import com.example.countryapp.constants.Const
import com.example.countryapp.databinding.ActivityChildBinding
import com.example.countryapp.ViewBindingActivity
import com.example.countryapp.application.CountryApplication
import com.example.countryapp.model.CountryLanguage
import com.example.countryapp.repository.impl.CountryRepositoryImpl
import com.example.countryapp.viewmodels.ChildViewModel
import com.example.countryapp.viewmodels.ViewModelFactory
import com.example.countryapp.viewmodels.states.ViewState
import kotlinx.android.synthetic.main.activity_child.*
import javax.inject.Inject

class ChildActivity : ViewBindingActivity<ActivityChildBinding>() {

    @Inject
    lateinit var countryQuery: CountryRepositoryImpl

    private lateinit var mainViewModel: ChildViewModel

    @SuppressLint("ResourceType")

    override fun setup() {
        (application as CountryApplication).appComponent.inject(this@ChildActivity)
        val name = intent.extras?.getString(Const.INTENT_COUNTRY_DETAILS_NAME).let { it ?: ""}
        mainViewModel = ViewModelProvider(this, ViewModelFactory(countryQuery, name)).get(ChildViewModel::class.java)
        mainViewModel.fetchCountryList()
        observeLiveData()
        showToolBarBackArrow(tb_child_activity)
    }

    private fun MutableList<String>?.getList(text: String) = if (this.isNullOrEmpty()) {
        mutableListOf(text)
    } else this

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    private fun observeLiveData() {
        mainViewModel.getCountryById().observe(this, Observer { response ->
            when (response) {
                is ViewState.Success -> {
                    response.value?.apply {
                        with(binding) {
                            tvChildActivityCountryName.text = countryName
                            tvChildActivityCountryEmoji.text = countryImage
                            tvChildActivityCountryCapital.text = countryCapital
                            tvChildActivityCountryRegion.text = countryRegion
                            tvChildActivityCountryPopulation.text = countryPopulation
                            countryCurrencies.getList(Const.CURRENCY_ERROR).forEach {
                                currenciesView.setNewTextView(
                                    it,
                                    R.drawable.currencies_background
                                )
                            }
                            setRecyclerView(countryLanguageList)
                            tvChildActivityCountryTimeZone.text = Const.FUTURE_FEATURE_MESSAGE
                            countryCalling.forEach {
                                callingCodeView.setNewTextView(
                                    it,
                                    R.drawable.callingcodde_background
                                )
                            }
                        }
                    }
                }
                is ViewState.Error -> {
                    Toast.makeText(this, Const.ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setRecyclerView(languageList: MutableList<CountryLanguage>) {
        val languageAdapter = LanguageAdapter(languageList, this@ChildActivity)
        binding.rvChildActivityCountryLanguage.adapter = languageAdapter
        languageAdapter.submitList(languageList)
    }

    private fun showToolBarBackArrow(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            this.title = ""
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getViewBinding() = ActivityChildBinding.inflate(layoutInflater)


}


