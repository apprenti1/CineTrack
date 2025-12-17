package fr.hainu.cinetrack.data.remote.models

import com.google.gson.annotations.SerializedName

/**
 * DTO pour la requête d'inscription
 */
data class RegisterRequestDto(
    @SerializedName("pseudo") val pseudo: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

/**
 * DTO pour la requête de connexion
 */
data class AuthRequestDto(
    @SerializedName("pseudo") val pseudo: String,
    @SerializedName("password") val password: String
)

/**
 * DTO pour la réponse d'authentification
 */
data class AuthResponseDto(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("user") val user: UserResponseDto
)

/**
 * DTO pour la réponse utilisateur
 */
data class UserResponseDto(
    @SerializedName("id") val id: String,
    @SerializedName("pseudo") val pseudo: String,
    @SerializedName("email") val email: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("watchlist") val watchlist: List<Int>,
    @SerializedName("likes") val likes: List<Int>,
    @SerializedName("watched") val watched: List<Int>
)

/**
 * DTO pour ajouter/supprimer un film d'une liste
 */
data class WatchlistRequestDto(
    @SerializedName("filmId") val filmId: Int
)
