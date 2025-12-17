package fr.hainu.cinetrack.data.repository

import android.util.Log
import fr.hainu.cinetrack.data.mapper.mapReviewResponseDtoToModel
import fr.hainu.cinetrack.data.remote.models.CreateReviewRequestDto
import fr.hainu.cinetrack.data.remote.models.UpdateReviewRequestDto
import fr.hainu.cinetrack.domain.models.ReviewModel
import fr.hainu.cinetrack.domain.repository.ReviewRepository
import fr.hainu.cinetrack.network.CineTrackApi

class ReviewRepositoryImpl(private val api: CineTrackApi) : ReviewRepository {

    override suspend fun createReview(filmId: Int, rating: Int, comment: String): Result<ReviewModel> {
        return try {
            // Log.d("ReviewRepository", "Creating review for film $filmId")
            val request = CreateReviewRequestDto(filmId, rating, comment)
            val response = api.createReview(request)
            val reviewModel = mapReviewResponseDtoToModel(response)
            // Log.d("ReviewRepository", "Review created successfully: ${reviewModel.id}")
            Result.success(reviewModel)
        } catch (e: Exception) {
            Log.e("ReviewRepository", "Error creating review", e)
            Result.failure(e)
        }
    }

    override suspend fun getMovieReviews(filmId: Int): Result<List<ReviewModel>> {
        return try {
            // Log.d("ReviewRepository", "Fetching reviews for film $filmId")
            val response = api.getMovieReviews(filmId)
            val reviews = response.map { mapReviewResponseDtoToModel(it) }
            // Log.d("ReviewRepository", "Fetched ${reviews.size} reviews")
            Result.success(reviews)
        } catch (e: Exception) {
            Log.e("ReviewRepository", "Error fetching movie reviews", e)
            Result.failure(e)
        }
    }

    override suspend fun getUserReviews(userId: String): Result<List<ReviewModel>> {
        return try {
            // Log.d("ReviewRepository", "Fetching reviews for user $userId")
            val response = api.getUserReviews(userId)
            val reviews = response.map { mapReviewResponseDtoToModel(it) }
            // Log.d("ReviewRepository", "Fetched ${reviews.size} user reviews")
            Result.success(reviews)
        } catch (e: Exception) {
            Log.e("ReviewRepository", "Error fetching user reviews", e)
            Result.failure(e)
        }
    }

    override suspend fun updateReview(reviewId: String, rating: Int, comment: String): Result<ReviewModel> {
        return try {
            // Log.d("ReviewRepository", "Updating review $reviewId")
            val request = UpdateReviewRequestDto(rating, comment)
            val response = api.updateReview(reviewId, request)
            val reviewModel = mapReviewResponseDtoToModel(response)
            // Log.d("ReviewRepository", "Review updated successfully")
            Result.success(reviewModel)
        } catch (e: Exception) {
            Log.e("ReviewRepository", "Error updating review", e)
            Result.failure(e)
        }
    }

    override suspend fun deleteReview(reviewId: String): Result<Unit> {
        return try {
            // Log.d("ReviewRepository", "Deleting review $reviewId")
            api.deleteReview(reviewId)
            // Log.d("ReviewRepository", "Review deleted successfully")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("ReviewRepository", "Error deleting review", e)
            Result.failure(e)
        }
    }
}
