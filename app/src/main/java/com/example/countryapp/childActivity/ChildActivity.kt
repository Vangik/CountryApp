package com.example.countryapp.childActivity

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.*
import com.example.countryapp.R
import com.example.countryapp.childActivity.adapter.LanguageAdapter
import com.example.countryapp.constants.CountryConst
import com.example.countryapp.databinding.ActivityChildBinding
import com.example.countryapp.ViewBindingActivity
import com.example.countryapp.model.CountryModel
import kotlinx.android.synthetic.main.activity_child.*

class ChildActivity : ViewBindingActivity<ActivityChildBinding>() {

    @SuppressLint("ResourceType")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun setNewTextView(llcurrencies: LinearLayout, text: String, backgroundColor: Int) {
        TextView(this@ChildActivity).apply {
            setTextColor(resources.getColor(R.color.mine_shaft))
            setBackgroundResource(backgroundColor)
            typeface = Typeface.create(CountryConst.MAIN_FONT, Typeface.BOLD)
            setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.details_textsize)
            )
            setPadding(12, 0, 12, 0)
            setText(text)
            RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 8, 0)
                layoutParams = this
            }
            llcurrencies.addView(this)
        }
    }

    override val bindingInflater: (LayoutInflater) -> ActivityChildBinding = ActivityChildBinding::inflate

    override fun setup() = with(binding) {
        val intent =
                intent.getSerializableExtra(CountryConst.INTENT_COUNTRY_DETAILS_NAME) as CountryModel

        intent.apply {
            tvChildActivityCountryName.text = countryName
            tvChildActivityCountryEmoji.text = countryImage
            tvChildActivityCountryCapital.text = countryCapital
            tvChildActivityCountryRegion.text = countryRegion
            tvChildActivityCountryPopulation.text = countryPopulation
            countryCurrencies.isListNullorEmpty(CountryConst.CURRENCY_ERROR).forEach { setNewTextView(llChildActivityCountryCurrencies, it, R.drawable.currencies_background) }

            tvChildActivityCountryTimeZone.text = CountryConst.FUTURE_FEATURE_MESSAGE
            countryCalling.forEach {
                setNewTextView(
                        llChildActivityCountryCallingCode,
                        it,
                        R.drawable.callingcodde_background
                )
            }
        }


        val languageAdapter = LanguageAdapter(intent.countryLanguageList, this@ChildActivity)
        rvChildActivityCountryLanguage.adapter = languageAdapter
        languageAdapter.submitList(intent.countryLanguageList)

        showToolBarBackArrow(tb_child_activity)
    }

    private fun MutableList<String>?.isListNullorEmpty(text: String): MutableList<String> {
        return if (this.isNullOrEmpty()) {
            mutableListOf(text)
        } else this
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

}