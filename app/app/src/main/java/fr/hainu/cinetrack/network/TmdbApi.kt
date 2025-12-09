package fr.hainu.cinetrack.network

import retrofit2.Response
import retrofit2.http.GET

interface TmdbApi {

    @GET("authentication")
    suspend fun checkAuthentication(): Response<Any>
}