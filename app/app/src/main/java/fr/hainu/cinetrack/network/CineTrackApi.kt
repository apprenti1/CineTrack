package fr.hainu.cinetrack.network

import fr.hainu.cinetrack.data.remote.models.AuthRequestDto
import fr.hainu.cinetrack.data.remote.models.AuthResponseDto
import fr.hainu.cinetrack.data.remote.models.RegisterRequestDto
import fr.hainu.cinetrack.data.remote.models.UserResponseDto
import fr.hainu.cinetrack.data.remote.models.WatchlistRequestDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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
}
