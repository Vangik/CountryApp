package com.example.sqlite.fragments

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sqlite.R
import com.example.sqlite.databinding.FragmentDetailsBinding
import com.example.sqlite.db.CountryDbManager
import com.example.sqlite.model.DataModel


class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private var dbManger: CountryDbManager? = null
    private var countryId: Int? = null
    private var details: DataModel? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        dbManger = context?.let { CountryDbManager(it) }
        dbManger?.openDb()
        setText()
        binding.backButton.setOnClickListener { backClick() }
        binding.updateButton.setOnClickListener { updateDb() }
    }

    private fun setText() {
        countryId = arguments?.getInt("countryId")
        details = countryId?.let { dbManger?.readDbGetDetails(it) }
        with(binding) {
            tvCountryName.text = details?.name
            etCapital.hint = details?.capital ?: "Capital not add"
            etRegion.hint = details?.region ?: "Region not add"
            etNative.hint = details?.native ?: "Native not add"
            etCurr.hint = details?.currency ?: "Currency not add"
            etLang.hint = details?.language ?: "Language not add"
        }
    }

    private fun updateDb() {
        dbManger?.updateDetails(
            with(binding) {
                DataModel(
                    countryId!!, "",
                    etCapital.editableText.returnNullOrText() ?: etCapital.hint.toString(),
                    etRegion.editableText.returnNullOrText() ?: etRegion.hint.toString(),
                    etNative.editableText.returnNullOrText() ?: etNative.hint.toString(),
                    etCurr.editableText.returnNullOrText() ?: etCurr.hint.toString(),
                    etLang.editableText.returnNullOrText() ?: etLang.hint.toString()
                )
            }
        )
        Toast.makeText(context, "Details updated", Toast.LENGTH_SHORT).show()
    }

    private fun backClick() {
        findNavController().popBackStack()
    }

    private fun Editable.returnNullOrText(): String? {
        if (this.isEmpty()) {
            return null
        }
        return this.toString()
    }

}