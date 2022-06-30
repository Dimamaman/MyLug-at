package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.Soz

@Dao
interface SozDao {
    @Query("SELECT * FROM mysoz")
    fun getAllSoz(): List<Soz>

    @Query("SELECT * FROM mysoz WHERE name LIKE :searchSoz")
    fun searchSoz(searchSoz: String): List<Soz>

    @Query("SELECT * FROM mysoz WHERE id = :id")
    fun getSozID(id: Int): Soz

    @Update
    fun update(soz: Soz)

    @Insert
    fun insert(soz: Soz)
}