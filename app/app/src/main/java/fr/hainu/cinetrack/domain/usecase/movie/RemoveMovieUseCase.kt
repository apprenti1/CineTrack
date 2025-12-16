package fr.hainu.cinetrack.domain.usecase.movie

import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.domain.repository.MovieRepository

class RemoveMovieUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movie : MovieModel): Boolean = repository.remove(movie)
}