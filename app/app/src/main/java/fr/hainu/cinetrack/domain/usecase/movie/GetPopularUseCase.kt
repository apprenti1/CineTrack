package fr.hainu.cinetrack.domain.usecase.movie

import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.domain.repository.MovieRepository

class GetPopularUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(page: Int = 1): List<MovieModel> = repository.getPopular(page)
}
