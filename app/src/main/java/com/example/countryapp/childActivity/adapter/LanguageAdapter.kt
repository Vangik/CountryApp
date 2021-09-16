package com.example.countryapp.childActivity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.R
import com.example.countryapp.model.CountryLanguage

class LanguageAdapter(private val languageList: MutableList<CountryLanguage>, val context: Context) :
    ListAdapter<CountryLanguage, LanguageAdapter.LanguageViewHolder>(LanguageDiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.language_details, parent, false)
        return LanguageViewHolder(inflater)
    }


    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val language = languageList[position]
        holder.bindItems(language)
    }


    class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(language: CountryLanguage) {
            itemView.findViewById<TextView>(R.id.tvLanguage).text = language.language
        }
    }
}