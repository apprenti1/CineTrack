package fr.hainu.cinetrack.domain.usecase.movie

import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.domain.repository.MovieRepository

class GetMovieDetailsUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movieId: Int): MovieModel? = repository.getMovieDetails(movieId)
}
