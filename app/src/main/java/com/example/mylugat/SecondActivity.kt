package com.example.mylugat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.data.model.Soz
import com.example.mylugat.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
//
//        val soz = intent.getParcelableExtra<Soz>(MainActivity.INTENT)
//
//        val title: TextView = findViewById(R.id.tvSecondActivity)
//        val desciption: TextView = findViewById(R.id.descriptionSecondActivity)
//
//        title.text = soz!!.name
//        desciption.text = soz.description
    }
}