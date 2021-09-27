package com.example.countryapp.childActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.*
import com.example.countryapp.R
import com.example.countryapp.childActivity.adapter.LanguageAdapter
import com.example.countryapp.constants.CountryConst
import com.example.countryapp.databinding.ActivityChildBinding
import com.example.countryapp.ViewBindingActivity
import com.example.countryapp.mainActivity.adapter.CountryAdapter
import com.example.countryapp.model.CountryLanguage
import com.example.countryapp.model.CountryModel
import kotlinx.android.synthetic.main.activity_child.*

class ChildActivity : ViewBindingActivity<ActivityChildBinding>(), ChildContract.View {


    private lateinit var languageList: MutableList<CountryLanguage>
    private val childPresenter = ChildPresenter( this)

    @SuppressLint("ResourceType")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setup() {
        setDetails()
        setRecyclerView()
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
        val intent =
            intent.getSerializableExtra(CountryConst.INTENT_COUNTRY_DETAILS_NAME) as CountryModel

        intent.apply {
            with(binding) {
                tvChildActivityCountryName.text = countryName
                tvChildActivityCountryEmoji.text = countryImage
                tvChildActivityCountryCapital.text = countryCapital
                tvChildActivityCountryRegion.text = countryRegion
                tvChildActivityCountryPopulation.text = countryPopulation
                countryCurrencies.getList(CountryConst.CURRENCY_ERROR).forEach {
                    childPresenter.setNewTextView(
                        llChildActivityCountryCurrencies, it,
                        R.drawable.currencies_background
                    )
                }
                countryLanguageList.let { languageList = it }
                println(languageList)
                tvChildActivityCountryTimeZone.text = CountryConst.FUTURE_FEATURE_MESSAGE
                countryCalling.forEach {
                    childPresenter.setNewTextView(
                        llChildActivityCountryCallingCode, it,
                        R.drawable.callingcodde_background
                    )
                }
            }
        }

    }

    private fun setRecyclerView() {
        val languageAdapter = LanguageAdapter(languageList, this@ChildActivity)
        binding.rvChildActivityCountryLanguage.adapter = languageAdapter
        languageAdapter.submitList(languageList)
        showToolBarBackArrow(tb_child_activity)
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

}