package fr.hainu.cinetrack.data.remote

import fr.hainu.cinetrack.data.local.UserPreferencesManager
import fr.hainu.cinetrack.data.remote.models.AuthRequestDto
import fr.hainu.cinetrack.data.remote.models.AuthResponseDto
import fr.hainu.cinetrack.data.remote.models.RegisterRequestDto
import fr.hainu.cinetrack.data.remote.models.UserResponseDto
import fr.hainu.cinetrack.data.remote.models.WatchlistRequestDto
import fr.hainu.cinetrack.network.CineTrackApi
import fr.hainu.cinetrack.network.RetrofitInstance

class UserRemoteDataSource(private val userPrefs: UserPreferencesManager) {

    // API pour auth (sans token)
    private val authApi: CineTrackApi = RetrofitInstance.cineTrackApi.create(CineTrackApi::class.java)

    // API avec auth (avec token)
    private fun getAuthenticatedApi(): CineTrackApi {
        return RetrofitInstance.getCineTrackApiWithAuth(userPrefs).create(CineTrackApi::class.java)
    }

    // ============ AUTH ============
    suspend fun register(pseudo: String, email: String, password: String): AuthResponseDto {
        return authApi.register(RegisterRequestDto(pseudo, email, password))
    }

    suspend fun login(pseudo: String, password: String): AuthResponseDto {
        return authApi.login(AuthRequestDto(pseudo, password))
    }

    // ============ USERS ============
    suspend fun getProfile(): UserResponseDto {
        return getAuthenticatedApi().getProfile()
    }

    // ============ WATCHLIST ============
    suspend fun addToWatchlist(filmId: Int): UserResponseDto {
        return getAuthenticatedApi().addToWatchlist(WatchlistRequestDto(filmId))
    }

    suspend fun removeFromWatchlist(filmId: Int): UserResponseDto {
        return getAuthenticatedApi().removeFromWatchlist(filmId)
    }

    // ============ LIKES ============
    suspend fun addToLikes(filmId: Int): UserResponseDto {
        return getAuthenticatedApi().addToLikes(WatchlistRequestDto(filmId))
    }

    suspend fun removeFromLikes(filmId: Int): UserResponseDto {
        return getAuthenticatedApi().removeFromLikes(filmId)
    }

    // ============ WATCHED ============
    suspend fun addToWatched(filmId: Int): UserResponseDto {
        return getAuthenticatedApi().addToWatched(WatchlistRequestDto(filmId))
    }

    suspend fun removeFromWatched(filmId: Int): UserResponseDto {
        return getAuthenticatedApi().removeFromWatched(filmId)
    }
}