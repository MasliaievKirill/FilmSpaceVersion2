package com.masliaiev.filmspace.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.masliaiev.filmspace.R
import com.masliaiev.filmspace.databinding.ActivityMainBinding
import com.masliaiev.filmspace.presentation.fragments.MainFragment

class MainActivity : AppCompatActivity(), MainFragment.OnToFavouriteClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.fragment_container)
        binding.bottomNavigation.setupWithNavController(navController)
    }

    override fun onToFavouriteClick() {
        binding.bottomNavigation.selectedItemId = R.id.favourite_fragment
    }
}