package fr.hainu.cinetrack.data.remote.services

import fr.hainu.cinetrack.data.remote.models.MovieDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MovieApiService {
    // https://my-json-server.typicode.com/RamzyK//demo/notes

    //GET REQUEST
    @GET("api/movie")
    suspend fun getAllMovie(): List<MovieDto>

    @GET("api/movie/my-movie")
    suspend fun getUserMovie(): List<MovieDto>

    @GET("api/movie/{id}")
    suspend fun getMovieById(
        @Path("id") id: Int
    ): MovieDto

    //POST REQUEST

    @POST("api/movie")
    suspend fun createMovie(
        @Body list: MovieDto
    ): MovieDto

    //PUT REQUEST

    @PUT("api/movie/{id}")
    suspend fun updateMovie(
        @Path("id") id: Int,
        @Body note: MovieDto
    ):MovieDto

    //DELETE REQUEST

    @DELETE("api/movie/{id}")
    suspend fun deleteMovie(
        @Path("id") id: Int
    ): Response<Unit>

}