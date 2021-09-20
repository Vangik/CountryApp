package com.example.countryapp.childActivity

import android.annotation.SuppressLint
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
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
import com.example.countryapp.model.CountryLanguage
import com.example.countryapp.model.CountryModel
import kotlinx.android.synthetic.main.activity_child.*

class ChildActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChildBinding

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChildBinding.inflate(layoutInflater)

        val intent = intent.getSerializableExtra(CountryConst.Intent_Country_Details_Name) as CountryModel

        intent.apply {
            binding.tvCountryName.text = countryName
            binding.tvEmoji.text = countryImage
            binding.tvCountryCapital.text = countryCapital
            binding.tvCountryRegion.text = countryRegion
            binding.tvCountryPopulation.text = countryPopulation
            for (s in countryCurrencies ?: mutableListOf(CountryConst.Currency_Error)) setNewTextView(
                binding.llCountryCurrencies,
                s,
                R.drawable.currencies_background
            )
            binding.tvCountryTimeZone.text = CountryConst.Future_Feature_Message
            for (s in countryCalling) setNewTextView(
                binding.llCountryCallingCode,
                s,
                R.drawable.callingcodde_background
            )
        }

        if (intent.countryLanguageList.isEmpty()) {
            intent.countryLanguageList.add(CountryLanguage(CountryConst.Language_Error))
        }
        val languageAdapter = LanguageAdapter(intent.countryLanguageList, this)
        binding.rvCountryLanguage.adapter = languageAdapter
        languageAdapter.submitList(intent.countryLanguageList)

        setContentView(binding.root)
        showToolBarBackArrow(tbChildActivity)

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

    private fun setNewTextView(llcurrencies: LinearLayout, text: String, backgroundColor: Int) {
        val textView = TextView(this@ChildActivity)
        textView.setTextColor(resources.getColor(R.color.mine_shaft))
        textView.setBackgroundResource(backgroundColor)
        textView.typeface = Typeface.create(CountryConst.Main_Font, Typeface.BOLD)
        textView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(R.dimen.details_textsize)
        )
        textView.setPadding(12, 0, 12, 0)
        textView.text = text
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 8, 0)
        textView.layoutParams = params

        llcurrencies.addView(textView)
    }

}