package fr.hainu.cinetrack.domain.repository

import fr.hainu.cinetrack.data.remote.models.UserDto
import fr.hainu.cinetrack.domain.models.UserModel

interface UserRepository {
    suspend fun fetchAll(): List<UserDto>
    suspend fun fetchById(id: Int): UserModel?
    fun add(user: UserModel)
    fun remove(user: UserModel): Boolean
    fun update(updateUser: UserModel)
}