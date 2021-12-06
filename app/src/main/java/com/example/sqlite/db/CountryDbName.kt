package com.example.sqlite.db

import android.provider.BaseColumns

object CountryDbName {
    const val TABLE_NAME = "country_table"
    const val COLUMN_COUNTRY_NAME = "country_name"
    const val COLUMN_COUNTRY_CAPITAL = "country_capital"
    const val COLUMN_COUNTRY_REGION = "country_region"
    const val COLUMN_COUNTRY_NATIVE = "country_native"
    const val COLUMN_COUNTRY_CURRENCY = "country_currency"
    const val COLUMN_COUNTRY_LANGUAGE = "country_language"


    const val DATABASE_VERSION = 4
    const val DATABASE_NAME = "CountryDb.db"

    const val CREATE_TABLE =
        "CREATE TABLE $TABLE_NAME (${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                "$COLUMN_COUNTRY_NAME TEXT, $COLUMN_COUNTRY_CAPITAL TEXT, $COLUMN_COUNTRY_REGION TEXT, $COLUMN_COUNTRY_NATIVE TEXT, $COLUMN_COUNTRY_CURRENCY TEXT, $COLUMN_COUNTRY_LANGUAGE TEXT)"

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}