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
import com.example.countryapp.model.CountryModel
import kotlinx.android.synthetic.main.activity_child.*

class ChildActivity : AppCompatActivity() {

    private lateinit var llcurrencies: LinearLayout
    private lateinit var llcallingcode: LinearLayout

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)

        val test = listOf("aba", "aba", "aba", "aba", "aba", "aba", "aba")
        val intent = intent.getSerializableExtra("c") as CountryModel
        llcurrencies = findViewById(R.id.llCurrencies)
        llcallingcode = findViewById(R.id.llCallingCode)
        intent.apply {
            tvCountryName.text = countryName
            tvEmoji.text = countryImage
            tvCapital.text = countryCapital ?: "No capital"
            tvRegion.text = countryRegion
            tvPopulation.text = countryPopulation
            for (s in countryCurrencies ?: mutableListOf("No currencies")) setNewTextView(
                llcurrencies,
                s,
                R.drawable.currencies_background
            )
            tvTimeZone.text = "Will be added later ;)"
            for (s in countryCalling) setNewTextView(
                llcallingcode,
                s,
                R.drawable.callingcodde_background
            )
        }

        val languageAdapter = LanguageAdapter(intent.countryLanguageList, this)
        rvLanguage.adapter = languageAdapter
        languageAdapter.submitList(intent.countryLanguageList)

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
        textView.typeface = Typeface.create("sans-serif", Typeface.BOLD)
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