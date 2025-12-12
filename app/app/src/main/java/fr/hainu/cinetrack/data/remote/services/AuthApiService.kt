package fr.hainu.cinetrack.data.remote.services

import retrofit2.Response
import retrofit2.http.POST

interface AuthApiService {
    @POST("api/auth/register")
    suspend fun register(): Response<Unit>

    @POST("api/auth/login")
    suspend fun login(): Response<Unit>
}