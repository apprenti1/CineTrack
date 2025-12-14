package fr.hainu.cinetrack.domain.repository

import fr.hainu.cinetrack.data.remote.models.ListDto
import fr.hainu.cinetrack.domain.models.ListModel

interface ListRepository {
    suspend fun fetchAll(): List<ListModel>
    suspend fun fetchById(id: Int): ListModel?
    suspend fun fetchFromUser(id: Int): List<ListModel>
    suspend fun add(list: ListModel)
    suspend fun addToMovie(list: ListModel)
    suspend fun remove(list: ListModel): Boolean
    suspend fun update(list: ListModel)
}