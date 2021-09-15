package com.example.countryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countryList = ArrayList<Model>()

        countryList.add(Model("Japan", ))
        countryList.add(Model("Japan", ))
        countryList.add(Model("Japan", ))
        countryList.add(Model("Japan",))
        countryList.add(Model("Japan", ))

        val recyclerView: RecyclerView = findViewById(R.id.rv_main_activity)
        val countryAdapter = CountryAdapter(countryList, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = countryAdapter
    }
}