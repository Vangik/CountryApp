package com.example.countryapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter(val countryList:ArrayList<Model>, val context:Context) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.country_list_item, parent, false)
        return CountryViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindItems(countryList[position])
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(model: Model) {
            itemView.findViewById<TextView>(R.id.tv_main_activity_country_name).text = model.countryName
            itemView.findViewById<ImageView>(R.id.iv_main_activity_country_flag).setImageResource(model.countryImage)
        }
    }
}