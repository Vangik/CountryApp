package com.example.countryapp.fragments

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.countryapp.ViewBindingFragment
import com.example.countryapp.constants.Const
import com.example.countryapp.databinding.FragmentMainBinding
import com.example.countryapp.adapters.CountryAdapter
import com.example.countryapp.application.CountryApplication
import com.example.countryapp.di.components.DaggerAppComponent
import com.example.countryapp.model.CountryModel
import com.example.countryapp.viewmodels.MainViewModel
import com.example.countryapp.viewmodels.states.ViewState
import com.example.countryapp.mainActivity.MainActivity
import com.example.countryapp.model.util.toCountryModel
import com.example.countryapp.repository.impl.CountryRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class MainFragment : ViewBindingFragment<FragmentMainBinding>(), FragmentManager.OnBackStackChangedListener {

    @Inject
    lateinit var repository: CountryRepositoryImpl

    override fun setup() {
        DaggerAppComponent.builder().build().inject(this)
        fetchCountryList()
        activity?.supportFragmentManager?.addOnBackStackChangedListener(this)
    }


    private fun setRecyclerView(languageList: List<CountryModel>) {
        val countryAdapter = CountryAdapter(languageList, context)
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

    companion object {
        var fragment: MainFragment? = null
        fun newInstance() : MainFragment? {
            if (fragment == null){
                fragment = MainFragment()
            }
            return fragment
        }
    }

    override fun onBackStackChanged() {
        if(activity != null){
            if (activity!!.supportFragmentManager.backStackEntryCount < 1) {
                (activity as MainActivity).hideUpButton()
            }
        }
    }

    private fun fetchCountryList() {
        repository.getCountryList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.data?.countries?.map { it.toCountryModel() }?.let { setRecyclerView(it) }
            }, {
                Toast.makeText(context, Const.ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
            })
    }

    override fun getViewBinding() = FragmentMainBinding.inflate(layoutInflater)

}