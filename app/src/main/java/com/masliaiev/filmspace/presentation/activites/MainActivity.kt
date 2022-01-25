package com.masliaiev.filmspace.presentation.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.masliaiev.filmspace.R
import com.masliaiev.filmspace.databinding.ActivityMainBinding
import com.masliaiev.filmspace.presentation.fragments.FavouriteFragment
import com.masliaiev.filmspace.presentation.fragments.MainFragment
import com.masliaiev.filmspace.presentation.fragments.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainFragment = MainFragment()

    private val favouriteFragment by lazy { FavouriteFragment() }

    private val searchFragment by lazy { SearchFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, mainFragment)
            .commit()
        binding.bottomNavigation.setOnItemSelectedListener {
            if (!it.isChecked) {
                when (it.itemId) {
                    R.id.main_fragment -> {
                        supportFragmentManager.popBackStack()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, mainFragment)
                            .commit()
                    }
                    R.id.favourite_fragment -> {
                        supportFragmentManager.popBackStack()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, favouriteFragment)
                            .addToBackStack(null)
                            .commit()
                    }
                    R.id.search_fragment -> {
                        supportFragmentManager.popBackStack()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, searchFragment)
                            .addToBackStack(null)
                            .commit()
                    }
                }
            }
            true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        binding.bottomNavigation.menu.getItem(0).isChecked = true
    }


}