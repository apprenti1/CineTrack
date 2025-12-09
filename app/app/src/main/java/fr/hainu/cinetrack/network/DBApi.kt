package fr.hainu.cinetrack.network

import retrofit2.Response
import retrofit2.http.GET

interface DBApi {

    @GET("authentication")
    suspend fun checkAuthentication(): Response<Any>
}