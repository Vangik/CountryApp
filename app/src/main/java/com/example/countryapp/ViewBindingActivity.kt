package com.example.countryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class ViewBindingActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        setup()
    }

    abstract fun setup()
    abstract fun getViewBinding(): VB
}

abstract class ViewBindingFragment<VB : ViewBinding> : Fragment() {

    lateinit var binding: VB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        setup()
        return binding.root
    }

    abstract fun setup()
    abstract fun getViewBinding(): VB
}