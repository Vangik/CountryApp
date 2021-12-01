package com.example.sqlite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CountryDbHelper(context: Context): SQLiteOpenHelper(context, CountryDbNameClass.DATABASE_NAME, null, CountryDbNameClass.DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CountryDbNameClass.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(CountryDbNameClass.DROP_TABLE)
        onCreate(db)
    }
}