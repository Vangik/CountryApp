package com.example.countryapp.mainActivity

import android.view.View
import android.widget.Toast
import com.example.countryapp.CountryListQuery
import com.example.countryapp.ViewBindingActivity
import com.example.countryapp.application.CountryApplication
import com.example.countryapp.mainActivity.adapter.CountryAdapter
import com.example.countryapp.model.DbImpl.CountryDbImpl
import com.example.countryapp.model.CountryLanguage
import com.example.countryapp.model.CountryModel
import com.example.countryapp.constants.CountryConst
import com.example.countryapp.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import javax.inject.Inject


class MainActivity : ViewBindingActivity<ActivityMainBinding>() {

    @Inject lateinit var countryQuery: CountryDbImpl
    private val countryList: MutableList<CountryModel> = mutableListOf()

    private fun parseResponse(list: List<CountryListQuery.Country>?): MutableList<CountryModel> {
        list?.forEach { country ->
            val countryLanguageList: MutableList<CountryLanguage> = mutableListOf()
            countryList.add(with(country) {
                CountryModel(
                    name,
                    emoji,
                    capital ?: CountryConst.CAPITAL_ERROR,
                    continent.name,
                    native_,
                    currency?.split(" ")?.toMutableList(),
                    countryLanguageList = countryLanguageList.apply {
                        country.languages.forEach { language ->
                            add(CountryLanguage(language.name))
                        }
                    },
                    country.phone.split(",").toMutableList()
                )
            })
        }
        return countryList
    }

    private fun setRecyclerView() {
        val countryAdapter = CountryAdapter(countryList, this)
        binding.rvMainActivityCountryDetails.adapter = countryAdapter
        countryAdapter.submitList(countryList)
    }

    override fun setup(): Unit = with(binding) {
        pbMainActivity.visibility = View.VISIBLE
        countryQuery.getCountryList().subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                parseResponse(it.data?.countries)
                setRecyclerView()
                },{
                Toast.makeText(
                    this@MainActivity,
                    CountryConst.ERROR_MESSAGE,
                    Toast.LENGTH_SHORT
                ).show()
               },{
                pbMainActivity.visibility = View.GONE
              })
    }  

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

}