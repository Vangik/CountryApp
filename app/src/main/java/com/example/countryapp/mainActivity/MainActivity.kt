package com.example.countryapp.mainActivity

import android.view.View
import android.widget.Toast
import com.example.countryapp.ViewBindingActivity
import com.example.countryapp.application.CountryApplication
import com.example.countryapp.mainActivity.adapter.CountryAdapter
import com.example.countryapp.network.DbImpl.CountryDbImpl
import com.example.countryapp.model.CountryModel
import com.example.countryapp.constants.Const
import com.example.countryapp.databinding.ActivityMainBinding
import javax.inject.Inject


class MainActivity : ViewBindingActivity<ActivityMainBinding>(), MainContract.View {

    @Inject lateinit var countryQuery: CountryDbImpl

    private lateinit var mainPresenter: MainPresenter

    private var countryList: MutableList<CountryModel> = mutableListOf()

    override fun setup(): Unit = with(binding) {
        (application as CountryApplication).appComponent.inject(this@MainActivity)
        mainPresenter = MainPresenter(this@MainActivity, countryQuery)
        mainPresenter.getCountryList()

    }

    override fun showCountryList(list: MutableList<CountryModel>) {
        this.countryList = list
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val countryAdapter = CountryAdapter(countryList, this)
        binding.rvMainActivityCountryDetails.adapter = countryAdapter
        countryAdapter.submitList(countryList)
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun showProgressBar() {
        binding.pbMainActivity.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.pbMainActivity.visibility = View.GONE
    }

    override fun onError() {
        Toast.makeText(this@MainActivity, Const.ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
    }

}