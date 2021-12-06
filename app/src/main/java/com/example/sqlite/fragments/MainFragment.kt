package com.example.sqlite.fragments

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.sqlite.R
import com.example.sqlite.databinding.FragmentMainBinding
import com.example.sqlite.db.CountryDbManager
import com.example.sqlite.model.DataModel


class MainFragment : Fragment(R.layout.fragment_main) {

    lateinit var binding: FragmentMainBinding
    private var dbManger: CountryDbManager? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        dbManger = context?.let { CountryDbManager(it) }
        dbManger?.openDb()
        binding.buttonAddToDb.setOnClickListener { sendDataToDb() }
        binding.buttonShowDb.setOnClickListener { showDataFromDb() }
    }

    private fun sendDataToDb() {
        when {
            dbManger?.checkIsDataAlreadyInDb(binding.editText.text.toString()) == true -> {
                Toast.makeText(context, "Country is already exist", Toast.LENGTH_SHORT).show()
            }
            binding.editText.text.isNotEmpty() -> {
                dbManger?.insertToDb(binding.editText.text.toString())
                Toast.makeText(context, "${binding.editText.text} added", Toast.LENGTH_LONG).show()
                binding.editText.text.clear()
            }
            else -> {
                Toast.makeText(context, "Input country name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDataFromDb() {
        findNavController().navigate(R.id.action_mainFragment_to_childFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        dbManger?.closeDb()
    }
}