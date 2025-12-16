package fr.hainu.cinetrack.data.remote.services

import fr.hainu.cinetrack.data.remote.models.CastMemberDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CastMemberApiService {
    // https://my-json-server.typicode.com/RamzyK//demo/notes

    //GET REQUEST
    @GET("api/castmember")
    suspend fun getAllCastMember(): List<CastMemberDto>

    @GET("credit/{id}")
    suspend fun getCastMemberById(
        @Path("id") id: Int
    ): CastMemberDto

    //POST REQUEST

    @POST("api/castmember")
    suspend fun createCastMember(
        @Body castMembers: CastMemberDto
    ): CastMemberDto

    //PUT REQUEST

    @PUT("api/castmember/{id}")
    suspend fun updateCastMember(
        @Path("id") id: Int,
        @Body castMembers: CastMemberDto
    ):CastMemberDto

    //DELETE REQUEST

    @DELETE("api/castmember/{id}")
    suspend fun deleteCastMember(
        @Path("id") id: Int
    ): Response<Unit>

}