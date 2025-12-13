package fr.hainu.cinetrack.data.remote

import fr.hainu.cinetrack.data.remote.models.CastMemberDto
import fr.hainu.cinetrack.data.remote.services.CastMemberApiService
import fr.hainu.cinetrack.network.RetrofitInstance

class CastMemberRemoteDataSource {
    private val api = RetrofitInstance.apiTmdb

    private val castMemberService = api.create(CastMemberApiService::class.java)

    suspend fun fetchCastMemberList() = castMemberService.getAllCastMember()

    suspend fun fetchCastMemberById(id: Int) = castMemberService.getCastMemberById(id)
    suspend fun postCastMemberById(castMembers: CastMemberDto) = castMemberService.createCastMember(castMembers)

    suspend fun updateCastMember(id: Int, castMembers: CastMemberDto) = castMemberService.updateCastMember(id, castMembers)
    suspend fun removeCastMember(id: Int) = castMemberService.deleteCastMember(id)

}

/*prof :
class NoteRemoteDataSource {
    private val api = RetrofitInstance.api

    private val notesService = api.create(NoteApiService::class.java)

    // HTTP Call to get a list of notes
    suspend fun fetchNotes() = notesService.getAllNotes()

    // HTTP Call to get a note by its id
    suspend fun fetchNoteById(id: Int) = notesService.getNoteById(id)

}
*/