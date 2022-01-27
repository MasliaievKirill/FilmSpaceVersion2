package com.masliaiev.filmspace.presentation.activites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.masliaiev.filmspace.R
import com.masliaiev.filmspace.databinding.ActivityDetailBinding
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.presentation.view_models.DetailActivityViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[DetailActivityViewModel::class.java]
    }
    private var inFavourite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
                if (favouriteMovie == movie) {
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
            if (it != null && it.isNotEmpty()) {
                val trailer = it[0]
                binding.youtubePlayerView.addYouTubePlayerListener(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.cueVideo(trailer.key!!, 0F)
                    }
                })
            } else {
                binding.youtubePlayerView.visibility = View.INVISIBLE
            }
        }
        lifecycle.addObserver(binding.youtubePlayerView)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.youtubePlayerView.release()
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