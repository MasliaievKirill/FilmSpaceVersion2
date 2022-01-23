package com.masliaiev.filmspace.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.masliaiev.filmspace.R
import com.masliaiev.filmspace.databinding.ActivityMainBinding
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigation.setOnItemSelectedListener {
            val fragment = when(it.itemId){
                R.id.main_fragment -> MainFragment.newInstanceMain()
                R.id.favourite_fragment -> FavouriteFragment.newInstanceFavourite()
                R.id.search_fragment -> SearchFragment.newInstanceSearch()
                else -> throw RuntimeException("Unknown fragment id $it.itemId")
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
            true
        }


    }
}