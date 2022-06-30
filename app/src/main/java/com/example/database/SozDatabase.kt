package com.example.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.dao.SozDao
import com.example.data.model.Soz


@Database(entities = [Soz::class], version = 6)
abstract class SozDatabase: RoomDatabase() {

    companion object {
        fun getInstance(context: Context): SozDatabase {
            return Room.databaseBuilder(
                context,
                SozDatabase::class.java,
                "mylugat.db"
            )
                .createFromAsset("mylugat.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun sozDao(): SozDao
}