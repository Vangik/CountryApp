package com.example.countryapp.childActivity.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.countryapp.model.CountryLanguage

class LanguageDiffUtilCallback : DiffUtil.ItemCallback<CountryLanguage>() {
    override fun areItemsTheSame(oldItem: CountryLanguage, newItem: CountryLanguage) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: CountryLanguage, newItem: CountryLanguage) =
        oldItem == newItem

}