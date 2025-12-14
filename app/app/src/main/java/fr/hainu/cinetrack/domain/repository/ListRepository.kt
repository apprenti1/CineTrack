package fr.hainu.cinetrack.domain.repository

import fr.hainu.cinetrack.data.remote.models.ListDto
import fr.hainu.cinetrack.domain.models.ListModel

interface ListRepository {
    suspend fun fetchAll(): List<ListDto>
    suspend fun fetchById(id: Int): ListModel?
    suspend fun fetchFromUser(id: Int): ListModel?
    fun add(list: ListModel)
    fun addToMovie(list: ListModel)
    fun remove(list: ListModel): Boolean
    fun update(updateList: ListModel)
}