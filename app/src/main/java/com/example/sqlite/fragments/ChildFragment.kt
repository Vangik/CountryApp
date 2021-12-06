package com.example.sqlite.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sqlite.R
import com.example.sqlite.databinding.FragmentChildBinding
import com.example.sqlite.db.CountryDbManager
import com.example.sqlite.model.DataModel
import com.example.sqlite.rv.CountryAdapter

class ChildFragment : Fragment(R.layout.fragment_child), CountryAdapter.RvOnclickListener {

    lateinit var binding: FragmentChildBinding
    private var dbManger: CountryDbManager? = null
    private var countryList: MutableList<DataModel>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChildBinding.bind(view)
        dbManger = context?.let { CountryDbManager(it) }
        dbManger?.openDb()
        setRv()
        Log.v("Tag", arguments?.getInt("countryId").toString())
    }


    private fun setRv() {
        countryList = dbManger?.readDb()
        val countryAdapter = dbManger?.readDb()?.let { CountryAdapter(it, context, this) }
        binding.rvCountryName.adapter = countryAdapter
        countryAdapter?.submitList(countryList)
    }

    override fun onDestroy() {
        super.onDestroy()
        dbManger?.closeDb()
    }

    override fun onClick(id: Int) {
        findNavController().navigate(
            R.id.action_childFragment_to_detailsFragment,
            bundleOf("countryId" to id)
        )
    }
}