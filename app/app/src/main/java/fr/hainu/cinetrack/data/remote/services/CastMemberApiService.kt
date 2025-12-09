package fr.hainu.cinetrack.data.remote.services

import fr.hainu.cinetrack.data.remote.models.CastMemberDto

import retrofit2.http.GET
import retrofit2.http.Path

interface CastMemberApiService {
    // https://my-json-server.typicode.com/RamzyK//demo/notes
    @GET("demo/notes")
    suspend fun getAllNotes(): List<NoteDto>

    @GET("demo/notes/{id}")
    suspend fun getNoteById(
        @Path("id") id: Int
    ): NoteDto

    //Work in prigress (prof exemple)
}