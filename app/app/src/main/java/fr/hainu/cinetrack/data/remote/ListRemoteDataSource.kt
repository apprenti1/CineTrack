package fr.hainu.cinetrack.data.remote

import fr.hainu.cinetrack.data.remote.models.ListDto
import fr.hainu.cinetrack.data.remote.services.ListApiService
import fr.hainu.cinetrack.network.RetrofitInstance

class ListRemoteDataSource {
    private val api = RetrofitInstance.apiTmdb

    private val castMemberService = api.create(ListApiService::class.java)

    suspend fun fetchListList() = castMemberService.getAllList()
    suspend fun fetchUserListList() = castMemberService.getUserList()

    suspend fun fetchListById(id: Int) = castMemberService.getListById(id)
    suspend fun postListById(list: ListDto) = castMemberService.createList(list)
    suspend fun postListToMovie(id: Int, list: ListDto) = castMemberService.addListToMovie(id, list)

    suspend fun updateList(id: Int, list: ListDto) = castMemberService.updateList(id, list)
    suspend fun removeList(id: Int) = castMemberService.deleteList(id)

}