package com.example.sqlite.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sqlite.db.dto.CountryDao
import com.example.sqlite.db.dto.CountryEntity


@Database(entities = [CountryEntity::class], version = 3)
abstract class AppDb : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}