package fr.hainu.cinetrack.data.repository

import fr.hainu.cinetrack.data.mapper.mapMovieDtoToModel
import fr.hainu.cinetrack.data.mapper.mapMovieModelToDto
import fr.hainu.cinetrack.data.mapper.mapUserDtoToModel
import fr.hainu.cinetrack.data.mapper.mapUserModelToDto
import fr.hainu.cinetrack.data.remote.UserRemoteDataSource
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.domain.repository.UserRepository
import fr.hainu.cinetrack.domain.models.UserModel

class UserRepositoryImpl(
    val remote: UserRemoteDataSource = UserRemoteDataSource()
) : UserRepository {


    override suspend fun fetchAll(): List<UserModel> {
        val dto = remote.fetchUsers()
        return mapUserDtoToModel(dto)
    }

    override suspend fun fetchById(id: Int): UserModel? {
        val dto = remote.fetchById(id)
        return mapUserDtoToModel(dto)
    }

    override suspend fun fetchMovies(id : Int): List<MovieModel> {
        val dto = remote.fetchMovieList(id)
        return mapMovieDtoToModel(dto)
    }

    override suspend fun add(user: UserModel) {
        val dto = mapUserModelToDto(user)
        remote.postUser(dto)
    }
    override suspend fun remove(user: UserModel): Boolean {
        remote.removeUser(user.id)
        return true
    }

    override suspend fun update(user: UserModel) {
        val dto = mapUserModelToDto(user)
        remote.updateUser(dto.id, dto)
    }
}