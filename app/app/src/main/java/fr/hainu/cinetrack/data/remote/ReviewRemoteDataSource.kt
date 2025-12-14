package fr.hainu.cinetrack.data.remote

import fr.hainu.cinetrack.data.remote.models.ReviewDto
import fr.hainu.cinetrack.data.remote.services.ReviewApiService
import fr.hainu.cinetrack.network.RetrofitInstance

class ReviewRemoteDataSource {
    private val api = RetrofitInstance.apiTmdb

    private val reviewService = api.create(ReviewApiService::class.java)

    suspend fun fetchReviews() = reviewService.getAllReview()
    suspend fun fetchUserReviewById(id: Int) = reviewService.getUserReview(id)
    suspend fun fetchMovieReviewList(id: Int) = reviewService.getMovieReview(id)

    suspend fun fetchReviewById(id: Int) = reviewService.getReviewById(id)
    suspend fun postReview(id: Int, review: ReviewDto) = reviewService.createMovieReview(id, review)

    suspend fun updateReview(id: Int, review: ReviewDto) = reviewService.updateReview(id, review)
    suspend fun removeReview(id: Int) = reviewService.deleteReview(id)
}