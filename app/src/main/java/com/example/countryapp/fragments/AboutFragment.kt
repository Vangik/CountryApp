package com.example.countryapp.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.countryapp.R
import com.example.countryapp.databinding.FragmentAboutBinding
import com.example.countryapp.mainActivity.MainActivity

class AboutFragment: Fragment(R.layout.fragment_about) {

    lateinit var binding: FragmentAboutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutBinding.bind(view)
        binding.backButton.setOnClickListener { activity?.onBackPressed() }

    }

}