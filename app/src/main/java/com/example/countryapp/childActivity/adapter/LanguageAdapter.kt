package com.example.countryapp.childActivity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.R
import com.example.countryapp.databinding.LanguageDetailsBinding
import com.example.countryapp.model.CountryLanguage
import kotlinx.android.synthetic.main.language_details.view.*

class LanguageAdapter(
    private val languageList: MutableList<CountryLanguage>,
    private val context: Context
) :
    ListAdapter<CountryLanguage, LanguageAdapter.LanguageViewHolder>(LanguageDiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val inflater = LanguageDetailsBinding.inflate(LayoutInflater.from(context), parent, false)
        return LanguageViewHolder(inflater)
    }


    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val language = languageList[position]
        holder.bindItems(language)
    }


    class LanguageViewHolder(itemView: LanguageDetailsBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        fun bindItems(language: CountryLanguage) {
            itemView.tvLanguage.text = language.language
        }
    }
}