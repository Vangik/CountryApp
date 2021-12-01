package com.example.sqlite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.sqlite.databinding.FragmentChildBinding
import com.example.sqlite.db.CountryDbManager

class ChildFragment : Fragment(R.layout.fragment_child) {

    lateinit var binding: FragmentChildBinding
    private var dbManger: CountryDbManager? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChildBinding.bind(view)
        dbManger = context?.let { CountryDbManager(it) }
        getResults()
    }

    private fun getResults() {
        dbManger?.openDb()
        dbManger?.readDb()?.forEach { binding.tvCountryList.append(it + "\n") }
        dbManger?.closeDb()
    }
}