package com.example.countryapp.fragments

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.countryapp.R
import com.example.countryapp.ViewBindingFragment
import com.example.countryapp.adapters.LanguageAdapter
import com.example.countryapp.constants.Const
import com.example.countryapp.databinding.FragmentChildBinding
import com.example.countryapp.mainActivity.MainActivity
import com.example.countryapp.model.CountryLanguage
import com.example.countryapp.viewmodels.MainViewModel
import com.example.countryapp.viewmodels.states.ViewState


class ChildFragment : ViewBindingFragment<FragmentChildBinding>() {

    var countryName: String? = null
    val dataModel: MainViewModel by activityViewModels()

    override fun setup() {
        dataModel.fetchCountryList(countryName)
        observeLiveData()
        showToolBarBackArrow(binding.tbChildActivity)
        (activity as MainActivity).showUpButton()
    }

    private fun MutableList<String>?.getList(text: String) = if (this.isNullOrEmpty()) {
        mutableListOf(text)
    } else this

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString(Const.INTENT_COUNTRY_DETAILS_NAME).let {
            countryName = it
        }
    }

    private fun observeLiveData() {
        dataModel.getCountry().observe(this, Observer { response ->
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
                    Toast.makeText(context, Const.ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setRecyclerView(languageList: MutableList<CountryLanguage>) {
        val languageAdapter = LanguageAdapter(languageList, context)
        binding.rvChildActivityCountryLanguage.adapter = languageAdapter
        languageAdapter.submitList(languageList)
    }

    private fun showToolBarBackArrow(toolbar: Toolbar) {
        with((activity as MainActivity)){
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                this.title = ""
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(name: String) = ChildFragment().apply {
            arguments = Bundle().apply {
                putString(Const.INTENT_COUNTRY_DETAILS_NAME, name)
            }
        }
    }

    override fun getViewBinding() = FragmentChildBinding.inflate(layoutInflater)
}