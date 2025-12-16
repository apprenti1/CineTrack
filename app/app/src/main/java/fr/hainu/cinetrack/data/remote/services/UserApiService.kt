package fr.hainu.cinetrack.data.remote.services

import fr.hainu.cinetrack.data.remote.models.MovieDto
import fr.hainu.cinetrack.data.remote.models.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApiService {
    // https://my-json-server.typicode.com/RamzyK//demo/notes

    //GET REQUEST
    @GET("users")
    suspend fun getAllUser(): List<UserDto>

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id: Int
    ): UserDto

    @GET("users/watchlist")
    suspend fun getUserMovies(
        @Path("movieId") id: Int
    ): List<MovieDto>

    //POST REQUEST

    @POST("users/newUser")
    suspend fun createUser(
        @Body list: UserDto
    ): UserDto

    //PUT REQUEST

    @PUT("users/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body note: UserDto
    ):UserDto

    //DELETE REQUEST

    @DELETE("users/{id}")
    suspend fun deleteUser(
        @Path("id") id: Int
    ): Response<Unit>

}