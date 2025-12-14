package fr.hainu.cinetrack.domain.repository

import fr.hainu.cinetrack.data.remote.models.MovieDto
import fr.hainu.cinetrack.domain.models.MovieModel

interface MovieRepository {
    suspend fun fetchAll(): List<MovieDto>
    suspend fun fetchById(id: Int): MovieModel?
    fun add(movie: MovieModel)
    fun remove(movie: MovieModel): Boolean
    fun update(updateMovie: MovieModel)
}