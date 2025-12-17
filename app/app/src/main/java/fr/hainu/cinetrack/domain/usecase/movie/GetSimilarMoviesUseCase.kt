package fr.hainu.cinetrack.domain.usecase.movie

import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.domain.repository.MovieRepository

class GetSimilarMoviesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movieId: Int, page: Int = 1): List<MovieModel> =
        repository.getSimilarMovies(movieId, page)
}
