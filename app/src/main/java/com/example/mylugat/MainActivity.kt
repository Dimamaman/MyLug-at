package com.example.mylugat

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
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
import com.example.data.model.Soz
import com.example.database.SozDatabase
import com.example.fragments.AboutFragment
import com.example.fragments.HomeFragment
import com.example.fragments.LikeFragment
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var badge: BadgeDrawable
    var likeDao: SozDao = SozDatabase.getInstance(this).sozDao()
    var count = likeDao.getAllFavorites().size
        set(value) {
            field = value
            badge.number = value
        }
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,HomeFragment()).commit()
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        likeDao = SozDatabase.getInstance(context = this).sozDao()

        badge = bottomNavigationView.getOrCreateBadge(R.id.like)
        badge.number  = likeDao.getAllFavorites().size

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {replaceFragment(HomeFragment())}

                R.id.like -> {
                    replaceFragment(LikeFragment())
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
