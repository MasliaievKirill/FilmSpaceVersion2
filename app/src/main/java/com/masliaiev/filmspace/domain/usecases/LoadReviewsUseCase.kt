package com.masliaiev.filmspace.domain.usecases

import com.masliaiev.filmspace.domain.entity.Review
import com.masliaiev.filmspace.domain.repository.MovieRepository
import javax.inject.Inject

class LoadReviewsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend fun loadReviews(movieId: Int): List<Review>? {
        return repository.loadReviews(movieId)
    }
}