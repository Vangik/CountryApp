package com.example.countryapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.countryapp.childActivity.ChildPresenter
import com.example.countryapp.constants.Const

class CountryDetailsBroadCast(val childPresenter: ChildPresenter) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        intent.getStringExtra(Const.INTENT_COUNTRY_DETAILS_NAME)?.let{
            childPresenter.fetchCountryDetails(it)
        }
        Toast.makeText(context, "Broadcast received",Toast.LENGTH_SHORT).show()
    }
}