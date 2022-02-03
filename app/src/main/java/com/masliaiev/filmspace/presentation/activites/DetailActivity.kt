package com.masliaiev.filmspace.presentation.activites

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.masliaiev.filmspace.R
import com.masliaiev.filmspace.databinding.ActivityDetailBinding
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.presentation.FilmSpaceApp
import com.masliaiev.filmspace.presentation.view_models.DetailActivityViewModel
import com.masliaiev.filmspace.presentation.view_models.ViewModelFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    private val component by lazy {
        (application as FilmSpaceApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailActivityViewModel
    private var inFavourite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailActivityViewModel::class.java]
        val movie = parseIntent()
        movie?.let {
            binding.movieInfo.tvTitle.text = it.title
            binding.movieInfo.tvOriginalTitle.text = it.originalTitle
            binding.movieInfo.tvRating.text = it.voteAverage.toString()
            binding.movieInfo.tvReleaseDate.text = it.releaseDate
            binding.movieInfo.tvOverview.text = it.overview
        }

        Picasso.get().load(movie?.posterPath).placeholder(R.drawable.placeholder_large)
            .into(binding.ivPoster)
        viewModel.favouriteMoviesList.observe(this) {
            for (favouriteMovie in it) {
                if (favouriteMovie.id == movie?.id) {
                    inFavourite = true
                    binding.ivAddRemoveFavourite.setImageResource(R.drawable.favourite_add_star_gold_small)
                }
            }
        }
        binding.ivAddRemoveFavourite.setOnClickListener {
            movie?.let {
                if (!inFavourite) {
                    viewModel.addMovieToFavourite(movie)
                    Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
                    binding.ivAddRemoveFavourite.setImageResource(R.drawable.favourite_add_star_gold_small)
                } else {
                    viewModel.deleteMovieFromFavourite(movie.id)
                    Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show()
                    binding.ivAddRemoveFavourite.setImageResource(R.drawable.favourite_add_star_white_small)
                }
            }

        }
        binding.toolbarDetail.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.toolbarDetail.title = movie?.title
        viewModel.loadTrailers(movie?.id!!)
        viewModel.trailersList.observe(this) {
            if (it.isNotEmpty()) {
                for (trailer in it) {
                    if (trailer.official != null && trailer.official) {
                        binding.trailerInfo.visibility = View.VISIBLE
                        binding.trailerInfo.setOnClickListener {
                            val intentYouTube = Intent(Intent.ACTION_VIEW, Uri.parse(trailer.key))
                            startActivity(intentYouTube)
                        }
                        binding.tvTrailer.text = trailer.name
                        break
                    }
                }
            }
        }

    }


    private fun parseIntent() =
        intent.getBundleExtra(EXTRA_MOVIE_BUNDLE)?.getParcelable<Movie>(EXTRA_MOVIE)


    companion object {

        private const val EXTRA_MOVIE = "movie"
        private const val EXTRA_MOVIE_BUNDLE = "movie_bundle"

        fun newIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_BUNDLE, Bundle().apply {
                    putParcelable(EXTRA_MOVIE, movie)
                })
            }
            return intent
        }
    }
}