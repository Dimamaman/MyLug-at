package com.example.data

import android.app.Application
import com.example.database.SozDatabase

class AppClass: Application() {
    lateinit var instance: AppClass
    override fun onCreate() {
        super.onCreate()
        instance = this
        SozDatabase.getInstance(this)
    }
}