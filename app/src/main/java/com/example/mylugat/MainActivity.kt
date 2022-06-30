package com.example.mylugat

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adapter.SozAdapter
import com.example.data.dao.SozDao
import com.example.database.SozDatabase
import com.example.fragments.AboutFragment
import com.example.fragments.HomeFragment
import com.example.fragments.LikeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    var count = 0
    // todo setterda badgeni ozgertiw

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,HomeFragment()).commit()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> { replaceFragment(HomeFragment()) }
                R.id.like -> {
                    replaceFragment(LikeFragment())
                    val badge = bottomNavigationView.getOrCreateBadge(R.id.like)
                    badge.number = count
                }
                R.id.about -> { replaceFragment(AboutFragment()) }
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }
}
