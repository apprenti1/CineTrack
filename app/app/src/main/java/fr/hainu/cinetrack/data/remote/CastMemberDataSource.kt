package fr.hainu.cinetrack.data.remote

import fr.hainu.cinetrack.data.remote.services.CastMemberApiService
import fr.hainu.cinetrack.network.RetrofitInstance

class NoteRemoteDataSource {
    private val api = RetrofitInstance.apiDB

    private val castMemberService = api.create(CastMemberApiService::class.java)

    suspend fun fetchCastMemberList() = castMemberService.getAllCastMember()

    suspend fun fetchCastMemberById(id: Int) = castMemberService.getCastMemberById(id)

}