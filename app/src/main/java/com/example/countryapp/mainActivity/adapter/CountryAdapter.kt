package com.example.countryapp.mainActivity.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.childActivity.ChildActivity
import com.example.countryapp.constants.Const
import com.example.countryapp.databinding.CountryListItemBinding
import com.example.countryapp.model.CountryModel
import kotlinx.android.synthetic.main.country_list_item.view.*


class CountryAdapter(
    private val countryList: MutableList<CountryModel>, private val context: Context
) : ListAdapter<CountryModel, CountryAdapter.CountryViewHolder>(CountryDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = CountryListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CountryViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countryList[position]
        holder.bindItems(country)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChildActivity::class.java)
            intent.putExtra(Const.INTENT_COUNTRY_DETAILS_NAME, country.countryCode)
            context.startActivity(intent)
        }
    }


    inner class CountryViewHolder(itemView: CountryListItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        fun bindItems(country: CountryModel) {
            with(itemView) {
                tv_main_activity_country_name.text = country.countryName
                tv_main_activity_country_emoji.text = country.countryImage
                tv_main_activity_country_capital.text = country.countryCapital
                tv_main_activity_country_region.text = country.countryRegion
            }
        }
    }

}




