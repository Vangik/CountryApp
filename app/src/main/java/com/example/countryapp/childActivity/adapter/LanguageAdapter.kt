package com.example.countryapp.childActivity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.R
import com.example.countryapp.constants.Const
import com.example.countryapp.databinding.LanguageDetailsBinding
import com.example.countryapp.model.CountryLanguage

class LanguageAdapter(
    private val languageList: MutableList<CountryLanguage>,
    private val context: Context
) :
    ListAdapter<CountryLanguage, LanguageAdapter.LanguageViewHolder>(LanguageDiffUtilCallback()) {

    init {
        if (languageList.isEmpty()) {
            languageList.add(CountryLanguage(Const.LANGUAGE_ERROR))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val inflater = LanguageDetailsBinding.inflate(LayoutInflater.from(context), parent, false)
        return LanguageViewHolder(inflater)
    }


    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val language = languageList[position]
        holder.bindItems(language)
    }


    class LanguageViewHolder(private val binding: LanguageDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(language: CountryLanguage) {
            binding.tvLanguageItem.text = language.language
        }
    }
}