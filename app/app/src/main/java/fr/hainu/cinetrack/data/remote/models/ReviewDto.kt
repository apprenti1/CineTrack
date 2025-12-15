package fr.hainu.cinetrack.data.remote.models

data class ReviewDto(
    val id: Int,
    val comment: String,
    val rating: Int,
    val refUser: Int,
    val refMovie: Int,
    val userDto: UserDto? = null,
    val movieDto: MovieDto? = null,
    val createdAt: String,
    val updatedAt: String
){}
