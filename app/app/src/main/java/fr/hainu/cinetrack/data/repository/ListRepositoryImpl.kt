package fr.hainu.cinetrack.data.repository

import fr.hainu.cinetrack.data.mapper.mapListDtoToListModel
import fr.hainu.cinetrack.data.mapper.mapListModelToListDto
import fr.hainu.cinetrack.data.remote.ListRemoteDataSource
import fr.hainu.cinetrack.domain.repository.ListRepository
import fr.hainu.cinetrack.domain.models.ListModel

class ListRepositoryImpl(
    val remote: ListRemoteDataSource = ListRemoteDataSource()
) : ListRepository {


    override suspend fun fetchAll(): List<ListModel> {
        val dto = remote.fetchLists()
        return mapListDtoToListModel(dto)
    }

    override suspend fun fetchFromUser(id: Int): List<ListModel> {
        val dto = remote.fetchFromUser(id)
        return mapListDtoToListModel(dto)
    }
    override suspend fun fetchById(id: Int): ListModel? {
        val dto = remote.fetchListById(id)
        return mapListDtoToListModel(dto)
    }

    override suspend fun add(list: ListModel) {
        val dto = mapListModelToListDto(list)
        remote.postList(dto)
    }

    override suspend fun addToMovie(list: ListModel) {
        val dto = mapListModelToListDto(list)
        remote.postToMovie(dto.id, dto)
    }

    override suspend fun remove(list: ListModel): Boolean {
        remote.removeList(list.id)
        return true
    }

    override suspend fun update(list: ListModel) {
        val dto = mapListModelToListDto(list)
        remote.updateList(dto.id, dto)

    }
}