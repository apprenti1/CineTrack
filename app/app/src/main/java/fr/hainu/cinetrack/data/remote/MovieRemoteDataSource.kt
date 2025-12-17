package fr.hainu.cinetrack.data.remote

import fr.hainu.cinetrack.data.remote.models.TmdbMovieDetailsDto
import fr.hainu.cinetrack.data.remote.models.TmdbMovieDto
import fr.hainu.cinetrack.network.RetrofitInstance
import fr.hainu.cinetrack.network.TmdbApi

class MovieRemoteDataSource {
    private val tmdbApi: TmdbApi = RetrofitInstance.tmdbApi.create(TmdbApi::class.java)

    // Récupérer les films tendance de la semaine
    suspend fun fetchTrendingWeek(): List<TmdbMovieDto> {
        return tmdbApi.getTrendingWeek().results
    }

    // Récupérer les films populaires
    suspend fun fetchPopular(page: Int = 1): List<TmdbMovieDto> {
        return tmdbApi.getPopular(page).results
    }

    // Récupérer les films récents
    suspend fun fetchNowPlaying(page: Int = 1): List<TmdbMovieDto> {
        return tmdbApi.getNowPlaying(page).results
    }

    // Rechercher des films
    suspend fun searchMovies(query: String, page: Int = 1): List<TmdbMovieDto> {
        return tmdbApi.searchMovies(query, page).results
    }

    // Récupérer les détails d'un film
    suspend fun fetchMovieDetails(movieId: Int): TmdbMovieDetailsDto {
        return tmdbApi.getMovieDetails(movieId)
    }

    // Récupérer plusieurs films par leurs IDs
    suspend fun fetchMoviesByIds(movieIds: List<Int>): List<TmdbMovieDetailsDto> {
        return movieIds.mapNotNull { id ->
            try {
                fetchMovieDetails(id)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    // Récupérer les films similaires
    suspend fun fetchSimilarMovies(movieId: Int, page: Int = 1): List<TmdbMovieDto> {
        return tmdbApi.getSimilarMovies(movieId, page).results
    }
}