package com.example.countryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.adapter.CountryAdapter
import com.example.countryapp.model.CountryModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var countryList = mutableListOf<CountryModel>()

        countryList.add(CountryModel("Albania", R.drawable.ic_de, "Tirane", "Asia", "Albanian","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum"))
        countryList.add(CountryModel("Algeria", R.drawable.ic_de, "Algiers", "Asia","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum"))
        countryList.add(CountryModel("Andorra", R.drawable.ic_de, "Andorra la Vella", "Asia","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum"))
        countryList.add(CountryModel("Angola", R.drawable.ic_de, "Luanda", "Asia","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum"))


        val recyclerView: RecyclerView = findViewById(R.id.rv_main_activity)
        val countryAdapter = CountryAdapter(countryList, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = countryAdapter
        countryAdapter.submitList(countryList)

    }
}