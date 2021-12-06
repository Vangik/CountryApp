package com.example.sqlite.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sqlite.MainActivity
import com.example.sqlite.R
import com.example.sqlite.databinding.FragmentChildBinding
import com.example.sqlite.db.CountryDbManager
import com.example.sqlite.db.dto.CountryEntity
import com.example.sqlite.model.DataModel
import com.example.sqlite.rv.CountryAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ChildFragment : Fragment(R.layout.fragment_child), CountryAdapter.RvOnclickListener {

    lateinit var binding: FragmentChildBinding
    private var dbManger: CountryDbManager? = null
    private var countryList: List<CountryEntity>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChildBinding.bind(view)
        getCountriesData()
        Log.v("Tag", arguments?.getInt("countryId").toString())
    }


    private fun getCountriesData() {
        (context as MainActivity).db.countryDao().getAll().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({ it ->
                val data = mutableListOf<DataModel>()
                if (!it.isNullOrEmpty()) {
                    it.forEach {
                        data.add(
                            DataModel(  it.cid, it.name, it.capital, it.region, it.test, it.currency, it.language)
                        )
                    }
                }
                setRv(data)
            }, {

            })


    }

    private fun setRv(data: MutableList<DataModel>) {
        val countryAdapter = CountryAdapter(data, context, this)
        binding.rvCountryName.adapter = countryAdapter
        countryAdapter.submitList(data)
    }

    override fun onDestroy() {
        super.onDestroy()
        //dbManger?.closeDb()
    }

    override fun onClick(id: Int, name: String) {

        findNavController().navigate(
            R.id.action_childFragment_to_detailsFragment,
            bundleOf("countryId" to id, "countryName" to name),
        )
    }
}