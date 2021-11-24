package com.example.countryapp.mainActivity

import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.countryapp.ViewBindingActivity
import com.example.countryapp.application.CountryApplication
import com.example.countryapp.viewmodels.MainViewModel
import com.example.countryapp.viewmodels.ViewModelFactory
import javax.inject.Inject
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.countryapp.R
import com.example.countryapp.databinding.ActivityMainBinding
import com.example.countryapp.fragments.MainFragment


class MainActivity : ViewBindingActivity<ActivityMainBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(
            this@MainActivity,
            viewModelFactory
        )[MainViewModel::class.java]
    }

    override fun setup(): Unit = with(binding) {
        (application as CountryApplication).appComponent.inject(this@MainActivity)
        mainViewModel.fetchCountryList()
        startFragment(MainFragment.newInstance(), R.id.fragment_container)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun startFragment(f: Fragment, idHolder: Int){
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder, f)
            .addToBackStack(null)
            .commit()
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

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

}