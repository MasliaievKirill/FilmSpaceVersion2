package com.masliaiev.filmspace.presentation.activites

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.masliaiev.filmspace.R

class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, EMPTY_EXTRA_MOVIE_ID)

    }


    companion object{

        private const val EXTRA_MOVIE_ID = "movie_id"
        private const val EMPTY_EXTRA_MOVIE_ID = 0

        fun newIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }
}