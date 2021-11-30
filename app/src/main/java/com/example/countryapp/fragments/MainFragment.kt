package com.example.countryapp.fragments

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.countryapp.R
import com.example.countryapp.constants.Const
import com.example.countryapp.adapters.CountryAdapter
import com.example.countryapp.databinding.FragmentMainBinding
import com.example.countryapp.di.components.DaggerAppComponent
import com.example.countryapp.model.CountryModel
import com.example.countryapp.model.util.toCountryModel
import com.example.countryapp.repository.impl.CountryRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class MainFragment : Fragment(R.layout.fragment_main),
    CountryAdapter.RvOnClockListener {

    private lateinit var binding: FragmentMainBinding
    private var list = emptyList<CountryModel>()

    @Inject
    lateinit var repository: CountryRepositoryImpl

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        DaggerAppComponent.builder().build().inject(this)
        fetchCountryList()
        binding.aboutButton.setOnClickListener { openAbout() }
    }

    private fun fetchCountryList() {
        repository.getCountryList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.data?.countries?.map { it.toCountryModel() }?.let { list = it }
                setRecyclerView(list)
            }, {
                Toast.makeText(context, Const.ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
            })
    }


    private fun setRecyclerView(languageList: List<CountryModel>) {
        val countryAdapter = CountryAdapter(languageList, context, this)
        binding.rvMainActivityCountryDetails.adapter = countryAdapter
        countryAdapter.submitList(languageList)
        binding.countrySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                countryAdapter.filter.filter(newText)
                return false
            }
        })
    }
    private fun openAbout(){
        findNavController().navigate(R.id.action_mainFragment_to_aboutFragment)
    }

    override fun onClick(position: Int) {
        findNavController().navigate(R.id.action_mainFragment_to_childFragment,
            bundleOf(Const.INTENT_COUNTRY_DETAILS_NAME to list[position].countryCode))
    }

}