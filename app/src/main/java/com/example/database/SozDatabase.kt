package com.example.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.dao.SozDao
import com.example.data.model.Soz


@Database(entities = [Soz::class], version = 7)
abstract class SozDatabase : RoomDatabase() {


    companion object {
        private var INSTANCE: SozDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): SozDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    context,
                    SozDatabase::class.java,
                    "mylugat.db"
                )
                    .createFromAsset("mylugat.db")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }

    abstract fun sozDao(): SozDao
}



