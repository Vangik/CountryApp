package com.example.sqlite.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sqlite.MainActivity
import com.example.sqlite.R
import com.example.sqlite.databinding.FragmentDetailsBinding
import com.example.sqlite.model.DataModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private var countryId: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        setHints()

        binding.sharePrefButton.setOnClickListener { sharePref()}
        binding.backButton.setOnClickListener { backClick() }
        binding.updateButton.setOnClickListener { updateCountryDetails() }
    }

    private fun setHints() {
        countryId = arguments?.getInt("countryId")
        (context as MainActivity).db.countryDao().getCountryNameById(countryId!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                with(binding) {
                    binding.tvCountryName.text = it.name
                    etCapital.hint = it.capital ?: "Capital not add"
                    etRegion.hint = it.region ?: "Region not add"
                    etNative.hint = it.test ?: "Native not add"
                    etCurr.hint = it.currency ?: "Currency not add"
                    etLang.hint = it.language ?: "Language not add"
                }
            }, {

            })
    }

    private fun updateCountryDetails() {
        with(binding) {
            (context as MainActivity).db.countryDao().updateDetails(
                countryId,
                etCapital.editableText.returnNullOrText() ?: etCapital.hint.toString(),
                etRegion.editableText.returnNullOrText() ?: etRegion.hint.toString(),
                etCurr.editableText.returnNullOrText() ?: etCurr.hint.toString(),
                etLang.editableText.returnNullOrText() ?: etLang.hint.toString(),
                etNative.editableText.returnNullOrText() ?: etNative.hint.toString()
                ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({})
        }
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
    private fun sharePref(){
        val text = binding.etSharePref.text
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        with(sharedPref!!.edit()){
            putString("name_key", text.toString())
            apply()
        }
        setText()
    }

    private fun setText(){
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val sharedId = sharedPref?.getInt("id_key",0)
        val name = sharedPref?.getString("name_key","default")
        binding.tvSharePref.text = name
    }
}