package com.example.countryapp.adapter

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.ChildActivity
import com.example.countryapp.CountryListQuery
import com.example.countryapp.R
import com.example.countryapp.model.CountryModel


class CountryAdapter(
    val countryList: List<CountryListQuery.Country>,
    val context: Context
) ://, val context: Context) :
    ListAdapter<CountryListQuery.Country, CountryAdapter.CountryViewHolder>(CountryDiffUtilCallback()) {
    //RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.country_list_item, parent, false)
        return CountryViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countryList.get(position)
        holder.bindItems(country)

        holder.itemView.setOnClickListener {
            val country = countryList.get(position)
            val intent = Intent(context, ChildActivity::class.java)
            val countryName = country.name
            val countryEmoji = country.emoji
            //intent.putExtra("country", country)
            intent.putExtra("countryName", countryName)
            intent.putExtra("countryEmoji", countryEmoji)
            context.startActivity(intent)
        }
    }

//    override fun getItemCount(): Int {
//        return countryList.size
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
//        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.country_list_item, parent, false)
//        return CountryViewHolder(viewHolder)
//    }
//
//    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
//        holder.bindItems(countryList[position])
//    }
//
//    override fun getItemCount(): Int {
//        return countryList.size
//    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(country: CountryListQuery.Country) {
            itemView.findViewById<TextView>(R.id.tv_main_activity_country_name).text =
                country.name
            itemView.findViewById<TextView>(R.id.iv_main_activity_country_flag).text = country.emoji
            itemView.findViewById<TextView>(R.id.tv_main_activity_country_capital).text =
                country.capital
            itemView.findViewById<TextView>(R.id.tv_main_activity_country_region).text =
                country.continent.name

        }
    }


}


