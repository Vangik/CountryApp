package com.example.countryapp.mainActivity.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.childActivity.ChildActivity
import com.example.countryapp.R
import com.example.countryapp.model.CountryModel


class CountryAdapter(val countryList: MutableList<CountryModel>, val context: Context) :
    ListAdapter<CountryModel, CountryAdapter.CountryViewHolder>(CountryDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.country_list_item, parent, false)
        return CountryViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countryList.get(position)
        holder.bindItems(country)
        holder.itemView.setOnClickListener {
            val country = countryList[position]
            val intent = Intent(context, ChildActivity::class.java)
            intent.putExtra("c", country)
            context.startActivity(intent)
        }
    }


    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(country: CountryModel) {
            itemView.findViewById<TextView>(R.id.tv_main_activity_country_name).text =
                country.countryName
            itemView.findViewById<TextView>(R.id.iv_main_activity_country_flag).text =
                country.countryImage
            itemView.findViewById<TextView>(R.id.tv_main_activity_country_capital).text =
                country.countryCapital
            itemView.findViewById<TextView>(R.id.tv_main_activity_country_region).text =
                country.countryRegion

        }
    }

}




