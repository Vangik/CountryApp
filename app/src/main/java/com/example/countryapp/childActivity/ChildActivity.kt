package com.example.countryapp.childActivity

import android.annotation.SuppressLint
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.*
import com.example.countryapp.R
import com.example.countryapp.childActivity.adapter.LanguageAdapter
import com.example.countryapp.constants.Const
import com.example.countryapp.databinding.ActivityChildBinding
import com.example.countryapp.ViewBindingActivity
import com.example.countryapp.application.CountryApplication
import com.example.countryapp.model.CountryLanguage
import com.example.countryapp.model.CountryModel
import com.example.countryapp.countryRepository.impl.CountryRepositoryImpl
import kotlinx.android.synthetic.main.activity_child.*
import javax.inject.Inject

class ChildActivity : ViewBindingActivity<ActivityChildBinding>(), ChildContract.View {


    private lateinit var languageList: MutableList<CountryLanguage>
    private lateinit var countryDetails: CountryModel
    private lateinit var childPresenter: ChildPresenter

    @Inject
    lateinit var countryRepositoryImpl: CountryRepositoryImpl

    @SuppressLint("ResourceType")

    override fun setup() {
        (application as CountryApplication).appComponent.inject(this@ChildActivity)

        childPresenter = ChildPresenter(this, countryRepositoryImpl)
        val name = intent.extras?.getString(Const.INTENT_COUNTRY_DETAILS_NAME)
        if (name != null) {
            childPresenter.fetchCountryDetails(name)
        }
        showToolBarBackArrow(tb_child_activity)

    }

    private fun MutableList<String>?.getList(text: String) = if (this.isNullOrEmpty()) {
        mutableListOf(text)
    } else this

    private fun showToolBarBackArrow(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            this.title = ""
        }
    }

    private fun setDetails() {
        countryDetails.apply {
            with(binding) {
                tvChildActivityCountryName.text = countryDetails.countryName
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
                countryLanguageList.let { languageList = it }
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    private fun setRecyclerView() {
        val languageAdapter = LanguageAdapter(languageList, this@ChildActivity)
        binding.rvChildActivityCountryLanguage.adapter = languageAdapter
        languageAdapter.submitList(languageList)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getViewBinding() = ActivityChildBinding.inflate(layoutInflater)

    override fun onError(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }


    override fun showCountryDetails(countryDetails: CountryModel) {
        this.countryDetails = countryDetails
        setDetails()
        setRecyclerView()
    }


}


