package com.example.countryapp.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.countryapp.R
import com.example.countryapp.ViewBindingActivity
import com.example.countryapp.databinding.MainSearchViewBinding

class MainSearchView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs){

    init {
        inflate(context, R.layout.main_search_view, this)
    }

}