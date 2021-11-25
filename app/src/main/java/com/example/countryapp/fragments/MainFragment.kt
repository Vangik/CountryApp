package com.example.countryapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.countryapp.R
import com.example.countryapp.constants.Const
import com.example.countryapp.adapters.CountryAdapter
import com.example.countryapp.databinding.FragmentMainBinding
import com.example.countryapp.model.CountryModel
import com.example.countryapp.viewmodels.MainViewModel
import com.example.countryapp.viewmodels.states.ViewState
import com.example.countryapp.mainActivity.MainActivity


class MainFragment : Fragment(), FragmentManager.OnBackStackChangedListener {

    private lateinit var binding: FragmentMainBinding
    private val dataModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )

        dataModel.fetchCountryList()
        activity?.supportFragmentManager?.addOnBackStackChangedListener(this)
        observeLiveData()

        return binding.root
    }

    private fun observeLiveData() {
        dataModel.getCountryList().observe(this, Observer { response ->
            when (response) {
                is ViewState.Loading -> {
                    binding.pbMainActivity.visibility = View.VISIBLE
                }
                is ViewState.Success -> {
                    binding.pbMainActivity.visibility = View.GONE
                    response.value?.let { setRecyclerView(it) }
                }
                is ViewState.Error -> {
                    Toast.makeText(context, Const.ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
                }
                else -> Toast.makeText(context, Const.ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
            }
        })
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
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun onBackStackChanged() {
        if(activity != null){
            if (activity!!.supportFragmentManager.backStackEntryCount < 1) {
                (activity as MainActivity).hideUpButton()
            }
        }
    }

}