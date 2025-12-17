package fr.hainu.cinetrack.domain.usecase.movie

import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.domain.repository.MovieRepository

class GetMoviesByIdsUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movieIds: List<Int>): List<MovieModel> = 
        repository.getMoviesByIds(movieIds)
}
