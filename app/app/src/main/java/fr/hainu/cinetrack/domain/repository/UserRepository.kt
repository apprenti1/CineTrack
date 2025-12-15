package fr.hainu.cinetrack.domain.repository

import fr.hainu.cinetrack.data.remote.models.UserDto
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.domain.models.UserModel

interface UserRepository {
    suspend fun fetchAll(): List<UserModel>
    suspend fun fetchById(id: Int): UserModel?
    suspend fun fetchMovies(id: Int): List<MovieModel>
    suspend fun add(user: UserModel)
    suspend fun remove(user: UserModel): Boolean
    suspend fun update(user: UserModel)
}