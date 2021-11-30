package com.example.countryapp.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.countryapp.model.CountryModel

class CountryDiffUtilCallback : DiffUtil.ItemCallback<CountryModel>() {
    override fun areItemsTheSame(oldItem: CountryModel, newItem: CountryModel) =
        oldItem.countryCode == newItem.countryCode

    override fun areContentsTheSame(oldItem: CountryModel, newItem: CountryModel) =
        areItemsTheSame(oldItem, newItem)
}