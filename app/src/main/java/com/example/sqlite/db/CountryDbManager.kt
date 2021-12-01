package com.example.sqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class CountryDbManager(context: Context) {
    private val countryDbHelper = CountryDbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb() {
        db = countryDbHelper.writableDatabase
    }

    fun insertToDb(name: String) {
        val value = ContentValues().apply {
            put(CountryDbNameClass.COLUMN_COUNTRY_NAME, name)
        }
        db?.insert(CountryDbNameClass.TABLE_NAME, null, value)
    }

    fun readDb(): ArrayList<String> {
        val dataList = ArrayList<String>()
        val cursor = db?.query(
            CountryDbNameClass.TABLE_NAME, null, null, null, null, null, null
        )

        with(cursor) {
            while (this?.moveToNext()!!) {
                val dataText =
                    getString(getColumnIndexOrThrow(CountryDbNameClass.COLUMN_COUNTRY_NAME))
                dataList.add(dataText.toString())
            }
        }
        cursor?.close()
        return dataList
    }

    fun closeDb() {
        countryDbHelper.close()
    }

    fun deleteAllFromDb(){
        db?.execSQL("delete FROM ${CountryDbNameClass.TABLE_NAME}")
    }

}