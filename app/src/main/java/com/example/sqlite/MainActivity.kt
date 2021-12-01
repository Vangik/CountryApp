package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqlite.db.CountryDbHelper
import com.example.sqlite.db.CountryDbManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}