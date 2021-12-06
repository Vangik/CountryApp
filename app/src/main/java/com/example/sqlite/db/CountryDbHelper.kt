package com.example.sqlite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CountryDbHelper(context: Context): SQLiteOpenHelper(context, CountryDbName.DATABASE_NAME, null, CountryDbName.DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CountryDbName.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(CountryDbName.DROP_TABLE)
        onCreate(db)
    }
}