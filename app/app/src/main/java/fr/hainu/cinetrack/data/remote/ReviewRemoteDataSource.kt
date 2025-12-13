package fr.hainu.cinetrack.data.remote

import fr.hainu.cinetrack.data.remote.models.ReviewDto
import fr.hainu.cinetrack.data.remote.services.ReviewApiService
import fr.hainu.cinetrack.network.RetrofitInstance

class ReviewRemoteDataSource {
    private val api = RetrofitInstance.apiTmdb

    private val reviewService = api.create(ReviewApiService::class.java)

    suspend fun fetchReviewReview() = reviewService.getAllReview()
    suspend fun fetchUserReviewReview(id: Int) = reviewService.getUserReview(id)
    suspend fun fetchMovieReviewReview(id: Int) = reviewService.getMovieReview(id)

    suspend fun fetchReviewById(id: Int) = reviewService.getReviewById(id)
    suspend fun postReviewById(id: Int, review: ReviewDto) = reviewService.createMovieReview(id, review)

    suspend fun updateReview(id: Int, review: ReviewDto) = reviewService.updateReview(id, review)
    suspend fun removeReview(id: Int) = reviewService.deleteReview(id)
}