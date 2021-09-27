package com.example.countryapp.childActivity

import android.widget.LinearLayout

interface ChildContract {
    interface View {
        fun onError(s: String)
    }

    interface Presenter {
        fun setNewTextView(llcurrencies: LinearLayout, text: String, backgroundColor: Int)
    }
}