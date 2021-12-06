package com.example.sqlite.db.dto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sqlite.db.CountryDbName
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CountryDao {
    @Query("select * from ${CountryDbName.TABLE_NAME}")
    fun getAll(): Single<List<CountryEntity>>

    @Insert
    fun insertCountry(country: CountryEntity) : Completable

    @Query("select * from ${CountryDbName.TABLE_NAME} where cid like :id")
    fun getCountryNameById(id: Int): Single<CountryEntity>

    @Query("update ${CountryDbName.TABLE_NAME} set c_capital = :capital, c_region = :region, c_currency= :currency, c_language= :language, test=:test where cid like :id")
    fun updateDetails(id: Int?, capital: String?, region: String?, currency: String?,
                      language: String?, test: String?
                    ) : Completable
}