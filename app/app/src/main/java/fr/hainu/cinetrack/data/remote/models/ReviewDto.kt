package fr.hainu.cinetrack.data.remote.models

data class ReviewDto(
    val id: Int,
    val userId: String,
    val filmId: Int,
    val rating: Float,
    val comment: String,
    val createdAt: String,
    val updatedAt: String
){}
