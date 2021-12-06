package com.example.sqlite.rv

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite.model.DataModel
import com.example.sqlite.databinding.ListItemBinding
import com.example.sqlite.db.dto.CountryEntity

class CountryAdapter(
    private val list: MutableList<DataModel>, private val context : Context?, private val listener: RvOnclickListener
) : ListAdapter<DataModel, CountryAdapter.CountryViewHolder>(DiffUtilCallback()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = ListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CountryViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        with(holder){
            bindItem(list[position])
        }
    }


    inner class CountryViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bindItem(item: DataModel){
                with(binding){
                    tvCountryName.text = item.name
                    tvCountryCapital.text = item.capital ?: "Capital not add"
                    tvCountryRegion.text = item.region ?: "Region not add"
                    tvCountryNative.text = item.native ?: "Native not add"
                    tvCountryCurrency.text = item.currency ?: "Currency not add"
                    tvCountryLanguage.text = item.language ?: "Language not add"
                }
                binding.buttonAddDetailsToDb.setOnClickListener {
                    listener.onClick(item.countryId, item.name)
                }
            }
        }
    interface RvOnclickListener{
        fun onClick(id: Int, name: String)
    }
}
