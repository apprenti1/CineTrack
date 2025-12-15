package fr.hainu.cinetrack.data.remote

import fr.hainu.cinetrack.data.remote.models.UserDto
import fr.hainu.cinetrack.data.remote.services.UserApiService
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.network.RetrofitInstance

class UserRemoteDataSource {
    private val api = RetrofitInstance.apiTmdb

    private val userService = api.create(UserApiService::class.java)

    suspend fun fetchUsers() = userService.getAllUser()

    suspend fun fetchById(id: Int) = userService.getUserById(id)
    suspend fun fetchMovieList(id: Int) = userService.getUserMovies(id)
    suspend fun postUser(user: UserDto) = userService.createUser(user)
    suspend fun updateUser(id: Int, user: UserDto) = userService.updateUser(id, user)
    suspend fun removeUser(id: Int) = userService.deleteUser(id)
}