package fr.hainu.cinetrack.data.repository

import fr.hainu.cinetrack.data.mapper.mapMovieDtoToMovieModel
import fr.hainu.cinetrack.data.mapper.mapMovieModelToMovieDto
import fr.hainu.cinetrack.data.mapper.mapUserDtoToUserModel
import fr.hainu.cinetrack.data.mapper.mapUserModelToUserDto
import fr.hainu.cinetrack.data.remote.UserRemoteDataSource
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.domain.repository.UserRepository
import fr.hainu.cinetrack.domain.models.UserModel

class UserRepositoryImpl(
    val remote: UserRemoteDataSource = UserRemoteDataSource()
) : UserRepository {


    override suspend fun fetchAll(): List<UserModel> {
        val dto = remote.fetchUsers()
        return mapUserDtoToUserModel(dto)
    }

    override suspend fun fetchById(id: Int): UserModel? {
        val dto = remote.fetchById(id)
        return mapUserDtoToUserModel(dto)
    }

    override suspend fun fetchMovies(id : Int): List<MovieModel> {
        val dto = remote.fetchMovieList(id)
        return mapMovieDtoToMovieModel(dto)
    }

    override suspend fun add(user: UserModel) {
        val dto = mapUserModelToUserDto(user)
        remote.postUser(dto)
    }
    override suspend fun remove(user: UserModel): Boolean {
        remote.removeUser(user.id)
        return true
    }

    override suspend fun update(user: UserModel) {
        val dto = mapUserModelToUserDto(user)
        remote.updateUser(dto.id, dto)
    }
}