package fr.hainu.cinetrack.domain.repository

import fr.hainu.cinetrack.domain.models.ReviewModel

interface ReviewRepository {
    suspend fun createReview(filmId: Int, rating: Int, comment: String): Result<ReviewModel>
    suspend fun getMovieReviews(filmId: Int): Result<List<ReviewModel>>
    suspend fun getUserReviews(userId: String): Result<List<ReviewModel>>
    suspend fun updateReview(reviewId: String, rating: Int, comment: String): Result<ReviewModel>
    suspend fun deleteReview(reviewId: String): Result<Unit>
}
