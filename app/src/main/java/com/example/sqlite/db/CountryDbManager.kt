package com.example.sqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.sqlite.model.DataModel

class CountryDbManager(context: Context) {
    private var details: DataModel? = null
    private val countryDbHelper = CountryDbHelper(context)
    private var db: SQLiteDatabase? = null

    fun openDb() {
        db = countryDbHelper.writableDatabase
    }

    fun insertToDb(name: String) {
        val value = ContentValues().apply {
            put(CountryDbName.COLUMN_COUNTRY_NAME, name)
//            put(CountryDbName.COLUMN_COUNTRY_CAPITAL, details.capital)
//            put(CountryDbName.COLUMN_COUNTRY_REGION, details.region)
//            put(CountryDbName.COLUMN_COUNTRY_NATIVE, details.native)
//            put(CountryDbName.COLUMN_COUNTRY_CURRENCY, details.currency)
//            put(CountryDbName.COLUMN_COUNTRY_LANGUAGE, details.capital)
        }
        try {
            db?.insert(CountryDbName.TABLE_NAME, null, value)
        } catch (e: SQLiteConstraintException) {
            Log.d("SQLite exception", "insertion failed", e)
        }
    }

    fun readDb(): MutableList<DataModel> {
        val dataList = mutableListOf<DataModel>()
        val cursor = db?.query(
            CountryDbName.TABLE_NAME, null, null, null, null, null, null
        )

        with(cursor) {
            while (this?.moveToNext()!!) {
                dataList.add(
                    DataModel(
                        getInt(getColumnIndexOrThrow("_id")),
                        getString(getColumnIndexOrThrow(CountryDbName.COLUMN_COUNTRY_NAME)),
                        getString(getColumnIndexOrThrow(CountryDbName.COLUMN_COUNTRY_CAPITAL)),
                        getString(getColumnIndexOrThrow(CountryDbName.COLUMN_COUNTRY_REGION)),
                        getString(getColumnIndexOrThrow(CountryDbName.COLUMN_COUNTRY_NATIVE)),
                        getString(getColumnIndexOrThrow(CountryDbName.COLUMN_COUNTRY_CURRENCY)),
                        getString(getColumnIndexOrThrow(CountryDbName.COLUMN_COUNTRY_LANGUAGE))
                        ))
            }
        }
        cursor?.close()
        return dataList
    }

    fun readDbGetDetails(id: Int): DataModel {
        val cursor = db?.query(
            CountryDbName.TABLE_NAME, null, "_id = $id", null, null, null, null
        )
        with(cursor) {
            while (this?.moveToNext()!!) {
                details = DataModel(
                    getInt(getColumnIndexOrThrow("_id")),
                    getString(getColumnIndexOrThrow(CountryDbName.COLUMN_COUNTRY_NAME)),
                    getString(getColumnIndexOrThrow(CountryDbName.COLUMN_COUNTRY_CAPITAL)),
                    getString(getColumnIndexOrThrow(CountryDbName.COLUMN_COUNTRY_REGION)),
                    getString(getColumnIndexOrThrow(CountryDbName.COLUMN_COUNTRY_NATIVE)),
                    getString(getColumnIndexOrThrow(CountryDbName.COLUMN_COUNTRY_CURRENCY)),
                    getString(getColumnIndexOrThrow(CountryDbName.COLUMN_COUNTRY_LANGUAGE))
                )
            }
        }
        cursor?.close()
        return details!!
    }

    fun updateDetails(details: DataModel) {
        val value = ContentValues().apply {
            put(CountryDbName.COLUMN_COUNTRY_CAPITAL, details.capital)
            put(CountryDbName.COLUMN_COUNTRY_REGION, details.region)
            put(CountryDbName.COLUMN_COUNTRY_NATIVE, details.native)
            put(CountryDbName.COLUMN_COUNTRY_CURRENCY, details.currency)
            put(CountryDbName.COLUMN_COUNTRY_LANGUAGE, details.language)
            Log.v("tag", details.countryId.toString())
        }
        try {
            db?.update(CountryDbName.TABLE_NAME, value, "_id = ?", arrayOf("${details.countryId}"))
        } catch (e: SQLiteConstraintException) {
            Log.d("SQLite exception", "update failed", e)
        }
    }

    fun closeDb() {
        countryDbHelper.close()
    }

    fun checkIsDataAlreadyInDb(s: String): Boolean {
        val query =
            "select * from ${CountryDbName.TABLE_NAME} where lower(${CountryDbName.COLUMN_COUNTRY_NAME}) = '${s.lowercase()}'"
        val cursor = db?.rawQuery(query, null)
        if (cursor?.count == 0) return false
        cursor?.close()
        return true
    }

    fun deleteAllFromDb() {
        db?.execSQL("delete FROM ${CountryDbName.TABLE_NAME}")
    }

}