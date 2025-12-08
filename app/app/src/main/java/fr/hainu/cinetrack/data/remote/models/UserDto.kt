package fr.hainu.cinetrack.data.remote.models

import fr.hainu.cinetrack.data.remote.models.ListDto
import fr.hainu.cinetrack.data.remote.models.ReviewDto

data class UserDto(
    val id: Int,
    val pseudo: String,
    val email: String,
    val password: String,
    val watchlist: List<Int> = emptyList(),
    val likes: List<Int> = emptyList(),
    val createdAt: String,
    val updatedAt: String,
    val reviews: List<ReviewDto> = emptyList(),
    val lists: List<ListDto> = emptyList()

) {
}