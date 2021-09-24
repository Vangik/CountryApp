package com.example.countryapp.mainActivity.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.countryapp.model.CountryModel

class CountryDiffUtilCallback : DiffUtil.ItemCallback<CountryModel>() {
    override fun areItemsTheSame(
        oldItem: CountryModel,
        newItem: CountryModel
    ): Boolean {
        return oldItem.countryName == newItem.countryName
    }

    override fun areContentsTheSame(
        oldItem: CountryModel,
        newItem: CountryModel
    ): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}