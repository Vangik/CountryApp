package com.example.countryapp.mainActivity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.countryapp.ViewBindingActivity
import com.example.countryapp.application.CountryApplication
import com.example.countryapp.viewmodels.MainViewModel
import com.example.countryapp.viewmodels.ViewModelFactory
import javax.inject.Inject
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.countryapp.R
import com.example.countryapp.fragments.MainFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}