package fr.hainu.cinetrack.data.remote.models

data class MovieDto(
    val id: Int,
    val title: String,
    val rating: Double,
    val posterUrl: String,
    val year: String,
    val genres: String,
    val gradientStartHex: String? = null,
    val gradientEndHex: String? = null,
    val ratingCoef: Int = 0,
    val duration: String = "",
    val synopsis: String = "",
    val trailerUrl: String = "",
    val internalCommentsAndRatings: List<ReviewDto> = emptyList()
){}