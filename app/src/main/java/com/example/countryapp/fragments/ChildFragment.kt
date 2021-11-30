package com.example.countryapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.countryapp.R
import com.example.countryapp.ViewBindingFragment
import com.example.countryapp.adapters.LanguageAdapter
import com.example.countryapp.constants.Const
import com.example.countryapp.databinding.FragmentChildBinding
import com.example.countryapp.di.components.DaggerAppComponent
import com.example.countryapp.mainActivity.MainActivity
import com.example.countryapp.model.CountryLanguage
import com.example.countryapp.model.CountryModel
import com.example.countryapp.model.util.toCountryModel
import com.example.countryapp.repository.impl.CountryRepositoryImpl
import com.example.countryapp.viewmodels.MainViewModel
import com.example.countryapp.viewmodels.states.ViewState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class ChildFragment : Fragment(R.layout.fragment_child) {

    var countryName: String? = null

    lateinit var binding: FragmentChildBinding

    @Inject
    lateinit var repository: CountryRepositoryImpl

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerAppComponent.builder().build().inject(this)
        binding = FragmentChildBinding.bind(view)
        countryName = requireArguments().getString(Const.INTENT_COUNTRY_DETAILS_NAME)
        fetchCountryList(countryName)
        binding.aboutButton.setOnClickListener { openAbout() }
        binding.backButton.setOnClickListener { activity?.onBackPressed() }

    }

    private fun MutableList<String>?.getList(text: String) = if (this.isNullOrEmpty()) {
        mutableListOf(text)
    } else this

    private fun observeLiveData(country: CountryModel?) {
        country?.apply {
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

    private fun fetchCountryList(name: String?) {
        name?.let {
            repository.getCountryById(it).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    observeLiveData(response.data?.country?.toCountryModel())
                }, {
                    Toast.makeText(context, Const.ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
                })
        }
    }

    private fun setRecyclerView(languageList: MutableList<CountryLanguage>) {
        val languageAdapter = LanguageAdapter(languageList, context)
        binding.rvChildActivityCountryLanguage.adapter = languageAdapter
        languageAdapter.submitList(languageList)
    }

    private fun openAbout() {
        findNavController().navigate(R.id.action_childFragment_to_aboutFragment)
    }



}