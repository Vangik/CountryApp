package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.sqlite.db.AppDb
import com.example.sqlite.db.CountryDbHelper
import com.example.sqlite.db.CountryDbManager
import com.example.sqlite.db.CountryDbName

class MainActivity : AppCompatActivity() {

    lateinit var db: AppDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = Room.databaseBuilder(applicationContext, AppDb::class.java, CountryDbName.DATABASE_NAME)
            .build()
    }
}