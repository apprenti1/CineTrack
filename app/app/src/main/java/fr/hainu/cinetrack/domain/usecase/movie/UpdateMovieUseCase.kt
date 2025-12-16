package fr.hainu.cinetrack.domain.usecase.movie

import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.domain.repository.MovieRepository

class UpdateMovieUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movie: MovieModel) {
        repository.update(movie)
    }
}