package fr.hainu.cinetrack.data.remote.services

import fr.hainu.cinetrack.data.remote.models.ListDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ListApiService {
    // https://my-json-server.typicode.com/RamzyK//demo/notes

    //GET REQUEST
    @GET("api/lists")
    suspend fun getAllList(): List<ListDto>

    @GET("api/lists/my-lists/{id}")
    suspend fun getUserList(
        @Path("id") id: Int
    ): List<ListDto>

    @GET("api/lists/{id}")
    suspend fun getListById(
        @Path("id") id: Int
    ): ListDto

    //POST REQUEST

    @POST("api/lists/new-list")
    suspend fun createList(
        @Body list: ListDto
    ): ListDto

    @POST("api/lists/movie/{movieId}")
    suspend fun addListToMovie(
        @Path("movieId") id: Int,
        @Body list: ListDto
    ): ListDto

    //PUT REQUEST

    @PUT("api/lists/{id}")
    suspend fun updateList(
        @Path("id") id: Int,
        @Body note: ListDto
    ):ListDto

    //DELETE REQUEST

    @DELETE("api/lists/{id}")
    suspend fun deleteList(
        @Path("id") id: Int
    ): Response<Unit>

}