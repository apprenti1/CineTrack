package fr.hainu.cinetrack.domain.usecase.movie

import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.domain.repository.MovieRepository

class GetAllUserMovieUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(): List<MovieModel> = repository.fetchFromUser()
}