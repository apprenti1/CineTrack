package fr.hainu.cinetrack.domain.repository

import fr.hainu.cinetrack.data.remote.models.MovieDto
import fr.hainu.cinetrack.domain.models.MovieModel

interface MovieRepository {
    suspend fun fetchAll(): List<MovieModel>
    suspend fun fetchFromUser(): List<MovieModel>
    suspend fun fetchById(id: Int): MovieModel?
    suspend fun add(movie: MovieModel)
    suspend fun remove(movie: MovieModel): Boolean
    suspend fun update(movie: MovieModel)
}