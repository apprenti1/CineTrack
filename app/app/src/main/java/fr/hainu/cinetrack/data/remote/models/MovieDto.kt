package fr.hainu.cinetrack.data.remote.models

data class MovieDto(
    val id: Int,
    val title: String,
    val rating: Double,
    val posterUrl: String?,
    val backdropUrl: String?,
    val year: String,
    val genres: String,
    val ratingCoef: Int,
    val duration: String,
    val synopsis: String?,
    val trailerUrl: String?,
    val cast: List<CastMemberDto>,
    val reviews: List<ReviewDto>,
    val isOnFavorite: Boolean,
    val isOnWatchlist: Boolean,
    val isOnWatched: Boolean,
    val isRated: Boolean,
    val isDetailed: Boolean
){}