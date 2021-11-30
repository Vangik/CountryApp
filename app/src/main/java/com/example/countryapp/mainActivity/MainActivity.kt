package com.example.countryapp.mainActivity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.countryapp.ViewBindingActivity
import com.example.countryapp.application.CountryApplication
import com.example.countryapp.viewmodels.MainViewModel
import com.example.countryapp.viewmodels.ViewModelFactory
import javax.inject.Inject
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.countryapp.R
import com.example.countryapp.databinding.ActivityMainBinding
import com.example.countryapp.fragments.CountryViewPagerAdapter
import com.example.countryapp.fragments.MainFragment
import java.text.FieldPosition


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var binding: ActivityMainBinding
//    val mainViewModel: MainViewModel by lazy {
//        ViewModelProvider(
//            this@MainActivity,
//            viewModelFactory
//        )[MainViewModel::class.java]
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as CountryApplication).appComponent.inject(this@MainActivity)
       // mainViewModel.fetchCountryList()
        startFragment(MainFragment.newInstance(), R.id.country_list)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun startFragment(f: Fragment?, idHolder: Int){
        f?.let {
            supportFragmentManager
                .beginTransaction()
                .replace(idHolder, it)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            supportFragmentManager.popBackStack()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun showUpButton(){ supportActionBar?.setDisplayHomeAsUpEnabled(true)}
    fun hideUpButton(){ supportActionBar?.setDisplayHomeAsUpEnabled(false)}


}