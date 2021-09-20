package com.example.countryapp.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.countryapp.mainActivity.adapter.CountryAdapter
import com.example.countryapp.model.CountryApolloAccess
import com.example.countryapp.model.CountryLanguage
import com.example.countryapp.model.CountryModel
import com.example.countryapp.constants.CountryConst
import com.example.countryapp.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val countryQuery = CountryApolloAccess()

    private val countryList: MutableList<CountryModel> = mutableListOf()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressMain.visibility = View.VISIBLE

        countryQuery.getCountryList().subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    response.data?.countries?.forEach { country ->
                        val countryLanguageList: MutableList<CountryLanguage> = mutableListOf()
                        countryList.add(with(country) {
                            CountryModel(
                                name,
                                emoji,
                                capital ?: CountryConst.Capital_Error,
                                continent.name,
                                native_,
                                currency?.split(",")?.toMutableList(),
                                countryLanguageList = countryLanguageList.apply {
                                    country.languages.forEach { language ->
                                        add(
                                            CountryLanguage(language.name)
                                        )
                                    }
                                },
                                country.phone.split(",").toMutableList()
                            )
                        }
                        )
                    }


                    val countryAdapter = CountryAdapter(countryList, this)
                    rvMainActivity.adapter = countryAdapter
                    countryAdapter.submitList(countryList)

                },
                {
                    Toast.makeText(this, CountryConst.Error_Message, Toast.LENGTH_SHORT).show()
                },
                {
                    binding.progressMain.visibility = View.GONE
                })

    }

}