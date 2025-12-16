package fr.hainu.cinetrack.domain.usecase.movie

import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.domain.repository.MovieRepository

class GetAllMovieUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(): List<MovieModel> = repository.fetchAll()
}