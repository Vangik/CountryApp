package com.example.countryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo.rx3.rxQuery
import com.example.countryapp.adapter.CountryAdapter
import com.example.countryapp.model.CountryModelList
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var countryList: List<CountryListQuery.Country> = emptyList()
    private val countryQuery = CountryModelList()
    private var progressBar: ProgressBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        progressBar= findViewById<ProgressBar>(R.id.progressMain) as ProgressBar
        progressBar?.visibility = View.VISIBLE

        countryQuery.getCountryList().subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response->
                response.data?.let { countryList = it.countries
                    progressBar?.visibility = View.GONE
                    val recyclerView: RecyclerView = findViewById(R.id.rv_main_activity)
                    val countryAdapter = CountryAdapter(countryList, this)
                    recyclerView.adapter = countryAdapter
                    countryAdapter.submitList(countryList)
                }
            },{

            },{

            })

    }

    private fun showCountryList(countryList: List<CountryListQuery.Country>) {
        this.countryList = countryList
    }
}