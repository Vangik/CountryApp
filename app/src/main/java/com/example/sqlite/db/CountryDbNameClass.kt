package com.example.sqlite.db

import android.provider.BaseColumns

object CountryDbNameClass {
    const val TABLE_NAME = "country_table"
    const val COLUMN_COUNTRY_NAME = "country_table"


    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "CountryDb.db"

    const val CREATE_TABLE =
        "CREATE TABLE $TABLE_NAME (${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_COUNTRY_NAME TEXT)"

    const val DROP_TABLE = "DROP TABLE IF EXIST $TABLE_NAME"
}