package fr.hainu.cinetrack.domain.repository

import fr.hainu.cinetrack.domain.models.UserModel

interface UserRepository {
    // ============ AUTH ============
    suspend fun register(pseudo: String, email: String, password: String): Result<Pair<String, UserModel>>
    suspend fun login(pseudo: String, password: String): Result<Pair<String, UserModel>>

    // ============ USERS ============
    suspend fun getProfile(): Result<UserModel>

    // ============ WATCHLIST ============
    suspend fun addToWatchlist(filmId: Int): Result<UserModel>
    suspend fun removeFromWatchlist(filmId: Int): Result<UserModel>

    // ============ LIKES ============
    suspend fun addToLikes(filmId: Int): Result<UserModel>
    suspend fun removeFromLikes(filmId: Int): Result<UserModel>

    // ============ WATCHED ============
    suspend fun addToWatched(filmId: Int): Result<UserModel>
    suspend fun removeFromWatched(filmId: Int): Result<UserModel>
}