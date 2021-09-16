package com.example.countryapp.childActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.R
import com.example.countryapp.childActivity.adapter.LanguageAdapter
import com.example.countryapp.model.CountryModel
import org.intellij.lang.annotations.Language

class ChildActivity : AppCompatActivity() {

    private val tvName: TextView by lazy { findViewById(R.id.tv_child_activity_country) }
    private val tvImage: TextView by lazy { findViewById(R.id.iv_child_activity_country_flag) }
    private val tvCapital: TextView by lazy { findViewById(R.id.tv_child_activity_capital) }
    private val tvRegion: TextView by lazy { findViewById(R.id.tv_child_activity_region) }
    private val tvPopulation: TextView by lazy { findViewById(R.id.tv_child_activity_population) }
    private val tvCurrencies: TextView by lazy { findViewById(R.id.tv_child_activity_currencies) }
    private val tvTimeZone: TextView by lazy { findViewById(R.id.tv_child_activity_timezone) }
    private val tvCalling: TextView by lazy { findViewById(R.id.tv_child_activity_callingcodes) }
    private val toolbar: Toolbar by lazy { findViewById(R.id.tb_child_activity) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)


        val intent = intent.getSerializableExtra("c") as CountryModel
        intent.apply {
            tvName.text = countryName
            tvImage.text = countryImage
            tvCapital.text = countryCapital ?: "No capital"
            tvRegion.text = countryRegion
            tvPopulation.text = countryPopulation
            tvCurrencies.text = countryCurrencies ?: "No currencies"
            tvTimeZone.text = "Will be added later ;)"
            tvCalling.text = countryCalling
        }
        val recyclerView: RecyclerView = findViewById(R.id.rv_child_activity)
        val languageAdapter = LanguageAdapter(intent.countryLanguageList, this)
        recyclerView.adapter = languageAdapter
        languageAdapter.submitList(intent.countryLanguageList)

        showToolBarBackArrow(toolbar)
    }

    private fun showToolBarBackArrow(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true);
            supportActionBar!!.setDisplayShowHomeEnabled(true);
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}