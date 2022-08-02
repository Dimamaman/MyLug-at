package com.example.data.dao

import androidx.room.*
import com.example.data.model.Soz

@Dao
interface SozDao {
    @Query("SELECT * FROM mysoz")
    fun getAllSoz(): List<Soz>

    @Query("SELECT * FROM mysoz WHERE name LIKE :searchSoz")
    fun searchSoz(searchSoz: String): List<Soz>

    @Query("SELECT * FROM mysoz WHERE id = :id")
    fun getSozID(id: Int): Soz

    @Query("SELECT * FROM mysoz WHERE is_favorite = 1")
    fun getAllFavorites(): MutableList<Soz>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSoz(soz: Soz)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSoz(soz: Soz)
}