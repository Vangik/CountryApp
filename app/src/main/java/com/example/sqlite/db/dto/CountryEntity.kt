package com.example.sqlite.db.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sqlite.db.CountryDbName


@Entity(tableName = CountryDbName.TABLE_NAME)
data class CountryEntity(

    @PrimaryKey(autoGenerate = true)
    var cid: Int = 0,

    @ColumnInfo(name = "c_name")
    var name: String,

    @ColumnInfo(name = "c_capital")
    var capital: String?,

    @ColumnInfo(name = "c_region")
    var region: String?,

    @ColumnInfo(name = "c_currency")
    var currency: String?,

    @ColumnInfo(name = "c_language")
    var language: String?,

    @ColumnInfo(name = "test")
    var test: String?
) {

    constructor() : this(0, "", "", "", "", "", "")
}