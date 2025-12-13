package fr.hainu.cinetrack.data.remote

import fr.hainu.cinetrack.data.remote.models.UserDto
import fr.hainu.cinetrack.data.remote.services.UserApiService
import fr.hainu.cinetrack.network.RetrofitInstance

class UserRemoteDataSource {
    private val api = RetrofitInstance.apiTmdb

    private val userService = api.create(UserApiService::class.java)

    suspend fun fetchUserUser() = userService.getAllUser()
    suspend fun fetchMovieUserUser(id: Int) = userService.getUserMovies(id)

    suspend fun fetchUserById(id: Int) = userService.getUserById(id)
    suspend fun postUserById(id: Int, user: UserDto) = userService.createMovieUser(id, user)
    suspend fun addWatchListMovies(id: Int) = userService.addWatchListMovies(id)

    suspend fun updateUser(id: Int, user: UserDto) = userService.updateUser(id, user)
    suspend fun removeUser(id: Int) = userService.deleteUser(id)
}