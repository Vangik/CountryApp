package com.example.countryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBar
import com.example.countryapp.model.CountryModel

class ChildActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)
        
//        val actionBar : ActionBar? = supportActionBar
//        actionBar!!.setDisplayHomeAsUpEnabled(true)
//        actionBar.setDisplayShowHomeEnabled(true)

        val countryName: TextView = findViewById(R.id.tv_child_activity_country)
        val countryImage: TextView = findViewById(R.id.iv_child_activity_country_flag)
        val countryCapital: TextView = findViewById(R.id.tv_child_activity_capital)
        val countryRegion: TextView = findViewById(R.id.tv_child_activity_region)
        val countryPopulation : TextView = findViewById(R.id.tv_child_activity_population)
        val countryCurrencies : TextView = findViewById(R.id.tv_child_activity_currencies)
        val countryLanguage: TextView = findViewById(R.id.tv_child_activity_language)
        val countryTimeZone: TextView = findViewById(R.id.tv_child_activity_timezone)
        val countryCalling: TextView = findViewById(R.id.tv_child_activity_callingcodes)

        val intent = intent
        //val country = intent.getParcelableExtra<CountryListQuery.Country>("country")
        val country = intent.getStringExtra("countryName")
        val countryEmoji = intent.getStringExtra("countryEmoji")
//        val intent = intent
//        val name = intent.getStringExtra("countryName")

         //   countryName.setText(name)

        countryName.setText(country)
        countryImage.setText(countryEmoji)
//        countryImage.setImageResource(model.countryImage)
//        countryCapital.setText(model.countryCapital)
//        countryRegion.setText(model.countryRegion)
//        countryPopulation.setText(model.countryPopulation)
//        countryCurrencies.setText(model.countryCurrencies)
//        countryLanguage.setText(model.countryLanguage)
//        countryTimeZone.setText(model.countryTimeZone)
//        countryCalling.setText(model.countryCalling)


        toolbar= findViewById(R.id.tb_child_activity)
        setSupportActionBar(toolbar)
        if (supportActionBar != null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(true);
            supportActionBar!!.setDisplayShowHomeEnabled(true);
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home){
           finish()
        }
        return super.onOptionsItemSelected(item)
    }

}