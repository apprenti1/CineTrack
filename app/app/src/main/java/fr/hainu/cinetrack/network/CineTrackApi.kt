package fr.hainu.cinetrack.network

import fr.hainu.cinetrack.data.remote.models.*
import retrofit2.http.*

interface CineTrackApi {

    // ============ AUTH ============
    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequestDto
    ): AuthResponseDto

    @POST("auth/login")
    suspend fun login(
        @Body request: AuthRequestDto
    ): AuthResponseDto

    // ============ USERS ============
    @GET("users/profile")
    suspend fun getProfile(): UserResponseDto

    // ============ WATCHLIST ============
    @POST("users/watchlist")
    suspend fun addToWatchlist(
        @Body request: WatchlistRequestDto
    ): UserResponseDto

    @DELETE("users/watchlist/{filmId}")
    suspend fun removeFromWatchlist(
        @Path("filmId") filmId: Int
    ): UserResponseDto

    // ============ LIKES ============
    @POST("users/likes")
    suspend fun addToLikes(
        @Body request: WatchlistRequestDto
    ): UserResponseDto

    @DELETE("users/likes/{filmId}")
    suspend fun removeFromLikes(
        @Path("filmId") filmId: Int
    ): UserResponseDto

    // ============ WATCHED ============
    @POST("users/watched")
    suspend fun addToWatched(
        @Body request: WatchlistRequestDto
    ): UserResponseDto

    @DELETE("users/watched/{filmId}")
    suspend fun removeFromWatched(
        @Path("filmId") filmId: Int
    ): UserResponseDto

    // ============ REVIEWS ============
    @POST("reviews")
    suspend fun createReview(
        @Body request: CreateReviewRequestDto
    ): ReviewResponseDto

    @GET("reviews/film/{filmId}")
    suspend fun getMovieReviews(
        @Path("filmId") filmId: Int
    ): List<ReviewResponseDto>

    @GET("reviews/user/{userId}")
    suspend fun getUserReviews(
        @Path("userId") userId: String
    ): List<ReviewResponseDto>

    @PUT("reviews/{id}")
    suspend fun updateReview(
        @Path("id") reviewId: String,
        @Body request: UpdateReviewRequestDto
    ): ReviewResponseDto

    @DELETE("reviews/{id}")
    suspend fun deleteReview(
        @Path("id") reviewId: String
    )
}
