package com.example.sqlite.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sqlite.MainActivity
import com.example.sqlite.R
import com.example.sqlite.databinding.FragmentMainBinding
import com.example.sqlite.db.CountryDbManager
import com.example.sqlite.db.dto.CountryEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class MainFragment : Fragment(R.layout.fragment_main) {

    lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.buttonAddToDb.setOnClickListener { saveDataToDb() }
        binding.buttonShowDb.setOnClickListener { showDataFromDb() }

    }

    private fun saveDataToDb() {

        (context as MainActivity).db.countryDao().insertCountry(
            CountryEntity(
                name = binding.editText.text.toString(),
                capital = null,
                region = null,
                currency = null,
                language = null,
                test = null
            )
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            {
                Toast.makeText(context, "${binding.editText.text} added", Toast.LENGTH_LONG).show()
                binding.editText.text.clear()


            }, {
                Toast.makeText(context, "Country is already exist", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun showDataFromDb() {
        findNavController().navigate(R.id.action_mainFragment_to_childFragment)
    }

}