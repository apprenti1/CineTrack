package fr.hainu.cinetrack.data.remote

import fr.hainu.cinetrack.data.remote.models.ListDto
import fr.hainu.cinetrack.data.remote.services.ListApiService
import fr.hainu.cinetrack.network.RetrofitInstance

class ListRemoteDataSource {
    private val api = RetrofitInstance.apiTmdb

    private val castMemberService = api.create(ListApiService::class.java)

    suspend fun fetchLists() = castMemberService.getAllList()
    suspend fun fetchFromUser(id: Int) = castMemberService.getUserList(id)

    suspend fun fetchListById(id: Int) = castMemberService.getListById(id)
    suspend fun postList(list: ListDto) = castMemberService.createList(list)
    suspend fun postToMovie(id: Int, list: ListDto) = castMemberService.addListToMovie(id, list)

    suspend fun updateList(id: Int, list: ListDto) = castMemberService.updateList(id, list)
    suspend fun removeList(id: Int) = castMemberService.deleteList(id)

}