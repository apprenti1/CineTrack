package fr.hainu.cinetrack.data.repository

import android.util.Log
import fr.hainu.cinetrack.data.mapper.mapUserResponseDtoToModel
import fr.hainu.cinetrack.data.remote.UserRemoteDataSource
import fr.hainu.cinetrack.domain.repository.UserRepository
import fr.hainu.cinetrack.domain.models.UserModel

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {

    // ============ AUTH ============
    override suspend fun register(pseudo: String, email: String, password: String): Result<Pair<String, UserModel>> {
        return try {
            val authResponse = remoteDataSource.register(pseudo, email, password)
            val userModel = mapUserResponseDtoToModel(authResponse.user)
            Result.success(Pair(authResponse.accessToken, userModel))
        } catch (e: Exception) {
            Log.e("UserRepository", "Register failed", e)
            Result.failure(e)
        }
    }

    override suspend fun login(pseudo: String, password: String): Result<Pair<String, UserModel>> {
        return try {
            val authResponse = remoteDataSource.login(pseudo, password)
            val userModel = mapUserResponseDtoToModel(authResponse.user)
            Result.success(Pair(authResponse.accessToken, userModel))
        } catch (e: Exception) {
            Log.e("UserRepository", "Login failed", e)
            Result.failure(e)
        }
    }

    // ============ USERS ============
    override suspend fun getProfile(): Result<UserModel> {
        return try {
            val userResponse = remoteDataSource.getProfile()
            val userModel = mapUserResponseDtoToModel(userResponse)
            Result.success(userModel)
        } catch (e: Exception) {
            Log.e("UserRepository", "Get profile failed", e)
            Result.failure(e)
        }
    }

    // ============ WATCHLIST ============
    override suspend fun addToWatchlist(filmId: Int): Result<UserModel> {
        return try {
            val userResponse = remoteDataSource.addToWatchlist(filmId)
            val userModel = mapUserResponseDtoToModel(userResponse)
            Result.success(userModel)
        } catch (e: Exception) {
            Log.e("UserRepository", "Add to watchlist failed", e)
            Result.failure(e)
        }
    }

    override suspend fun removeFromWatchlist(filmId: Int): Result<UserModel> {
        return try {
            val userResponse = remoteDataSource.removeFromWatchlist(filmId)
            val userModel = mapUserResponseDtoToModel(userResponse)
            Result.success(userModel)
        } catch (e: Exception) {
            Log.e("UserRepository", "Remove from watchlist failed", e)
            Result.failure(e)
        }
    }

    // ============ LIKES ============
    override suspend fun addToLikes(filmId: Int): Result<UserModel> {
        return try {
            val userResponse = remoteDataSource.addToLikes(filmId)
            val userModel = mapUserResponseDtoToModel(userResponse)
            Result.success(userModel)
        } catch (e: Exception) {
            Log.e("UserRepository", "Add to likes failed", e)
            Result.failure(e)
        }
    }

    override suspend fun removeFromLikes(filmId: Int): Result<UserModel> {
        return try {
            val userResponse = remoteDataSource.removeFromLikes(filmId)
            val userModel = mapUserResponseDtoToModel(userResponse)
            Result.success(userModel)
        } catch (e: Exception) {
            Log.e("UserRepository", "Remove from likes failed", e)
            Result.failure(e)
        }
    }

    // ============ WATCHED ============
    override suspend fun addToWatched(filmId: Int): Result<UserModel> {
        return try {
            val userResponse = remoteDataSource.addToWatched(filmId)
            val userModel = mapUserResponseDtoToModel(userResponse)
            Result.success(userModel)
        } catch (e: Exception) {
            Log.e("UserRepository", "Add to watched failed", e)
            Result.failure(e)
        }
    }

    override suspend fun removeFromWatched(filmId: Int): Result<UserModel> {
        return try {
            val userResponse = remoteDataSource.removeFromWatched(filmId)
            val userModel = mapUserResponseDtoToModel(userResponse)
            Result.success(userModel)
        } catch (e: Exception) {
            Log.e("UserRepository", "Remove from watched failed", e)
            Result.failure(e)
        }
    }
}