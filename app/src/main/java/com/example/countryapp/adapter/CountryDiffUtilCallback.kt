package com.example.countryapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.countryapp.CountryListQuery

class CountryDiffUtilCallback : DiffUtil.ItemCallback<CountryListQuery.Country>() {
    override fun areItemsTheSame(oldItem: CountryListQuery.Country, newItem: CountryListQuery.Country): Boolean {
        return oldItem.name==newItem.name
    }

    override fun areContentsTheSame(oldItem: CountryListQuery.Country, newItem: CountryListQuery.Country): Boolean {
        return oldItem.name==newItem.name
    }
}