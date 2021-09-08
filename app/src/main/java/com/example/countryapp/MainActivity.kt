package com.example.countryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.example.countryapp.adapter.CountryAdapter
import com.example.countryapp.model.CountryModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apolloClient =
            ApolloClient.builder().serverUrl("https://countries.trevorblades.com").build()

        val context = this
        lifecycleScope.launchWhenResumed {
            val response = apolloClient.query(CountryListQuery()).await()

            // val countries = response.data?.countries?.filterNotNull()
            val countries = response.data?.countries
            println("test $countries")
                val recyclerView: RecyclerView = findViewById(R.id.rv_main_activity)
                val countryAdapter = countries?.let { CountryAdapter(it,context) }
                recyclerView.adapter = countryAdapter
            if (countryAdapter != null) {
                countryAdapter.submitList(countries)
            }

        }


//        var countryList = mutableListOf<CountryModel>()
//
//        countryList.add(CountryModel("Albania", R.drawable.ic_de, "Tirane", "Asia", "Albanian","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum"))
//        countryList.add(CountryModel("Algeria", R.drawable.ic_de, "Algiers", "Asia","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum"))
//        countryList.add(CountryModel("Andorra", R.drawable.ic_de, "Andorra la Vella", "Asia","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum"))
//        countryList.add(CountryModel("Angola", R.drawable.ic_de, "Luanda", "Asia","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum","Lorem ipsum"))



    }
}