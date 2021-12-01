package com.example.sqlite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.sqlite.databinding.FragmentMainBinding
import com.example.sqlite.db.CountryDbManager


class MainFragment : Fragment(R.layout.fragment_main) {

    lateinit var binding: FragmentMainBinding
    private var dbManger: CountryDbManager? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.buttonAddToDb.setOnClickListener { setDataToDb() }
        binding.buttonShowDb.setOnClickListener { showDataFromDb() }
    }

    private fun setDataToDb() {
        dbManger = context?.let { CountryDbManager(it) }
        dbManger?.openDb()
        dbManger?.insertToDb(binding.editText.text.toString())
        dbManger?.closeDb()
    }

    private fun showDataFromDb() {
        findNavController().navigate(R.id.action_mainFragment_to_childFragment)
    }

}