package fr.hainu.cinetrack.domain.repository

import fr.hainu.cinetrack.domain.models.MovieModel

interface MovieRepository {
    // Films tendance de la semaine
    suspend fun getTrendingWeek(): List<MovieModel>

    // Films populaires
    suspend fun getPopular(page: Int = 1): List<MovieModel>

    // Films récents
    suspend fun getNowPlaying(page: Int = 1): List<MovieModel>

    // Recherche de films
    suspend fun searchMovies(query: String, page: Int = 1): List<MovieModel>

    // Détails d'un film
    suspend fun getMovieDetails(movieId: Int): MovieModel?

    // Récupérer plusieurs films par leurs IDs
    suspend fun getMoviesByIds(movieIds: List<Int>): List<MovieModel>

    // Films similaires
    suspend fun getSimilarMovies(movieId: Int, page: Int = 1): List<MovieModel>
}