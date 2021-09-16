package com.example.countryapp.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.R
import com.example.countryapp.mainActivity.adapter.CountryAdapter
import com.example.countryapp.model.CountryApolloAccess
import com.example.countryapp.model.CountryLanguage
import com.example.countryapp.model.CountryModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io

class MainActivity : AppCompatActivity() {

    private val countryQuery = CountryApolloAccess()
    private var progressBar: ProgressBar? = null
    private val countryList: MutableList<CountryModel> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        progressBar = findViewById<ProgressBar>(R.id.progressMain) as ProgressBar
        progressBar?.visibility = View.VISIBLE

        countryQuery.getCountryList().subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.data?.countries?.forEach { country ->
                    val countryLanguageList: MutableList<CountryLanguage> = mutableListOf()
                    countryList.add(
                        CountryModel(
                            country.name,
                            country.emoji,
                            country.capital,
                            country.continent.name,
                            country.native_,
                            country.currency,
                            countryLanguageList = countryLanguageList.apply {
                                country.languages.forEach { language ->
                                    add(
                                        CountryLanguage(language.name)
                                    )
                                }
                            },
                            country.phone
                        )

                    )

                }

                val recyclerView: RecyclerView = findViewById(R.id.rv_main_activity)
                val countryAdapter = CountryAdapter(countryList, this)
                recyclerView.adapter = countryAdapter
                countryAdapter.submitList(countryList)

            },
                {
                    Toast.makeText(this, "Check you internet connection", Toast.LENGTH_SHORT)
                },
                {
                    progressBar?.visibility = View.GONE
                })

    }

}