package com.example.countryapp.mainActivity.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.childActivity.ChildActivity
import com.example.countryapp.constants.Const
import com.example.countryapp.databinding.CountryListItemBinding
import com.example.countryapp.model.CountryModel
import java.util.*

class CountryAdapter(
    private val countryList: List<CountryModel>, private val context: Context
) : ListAdapter<CountryModel, CountryAdapter.CountryViewHolder>(CountryDiffUtilCallback()),
    Filterable {

    var countryFilterList = listOf<CountryModel>()

    init {
        countryFilterList = countryList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = CountryListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CountryViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countryFilterList[position]
        holder.bindItems(country)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChildActivity::class.java)
            intent.putExtra(Const.INTENT_COUNTRY_DETAILS_NAME, country.countryCode)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return countryFilterList.size
    }


    inner class CountryViewHolder(private val binding: CountryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(country: CountryModel) {
            with(binding) {
                tvMainActivityCountryName.text = country.countryName
                tvMainActivityCountryEmoji.text = country.countryImage
                tvMainActivityCountryCapital.text = country.countryCapital
                tvMainActivityCountryRegion.text = country.countryRegion
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    countryFilterList = countryList
                } else {
                    val resultList = mutableListOf<CountryModel>()
                    countryList.forEach {
                        if (it.countryName.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ){
                            resultList.add(it)
                        }
                    }
                    countryFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as MutableList<CountryModel>
                notifyDataSetChanged()
            }

        }
    }

}




