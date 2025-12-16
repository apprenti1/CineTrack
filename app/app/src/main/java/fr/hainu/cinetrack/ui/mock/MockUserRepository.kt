package fr.hainu.cinetrack.ui.mock

import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import fr.hainu.cinetrack.BuildConfig
import java.net.HttpURLConnection
import java.net.URL

class MockUserRepository {

    private val gson = Gson()
    private val baseUrl = "${BuildConfig.CINETRACK_API_URL}/api"

    data class RegisterRequest(
        val pseudo: String,
        val email: String,
        val password: String
    )

    data class LoginRequest(
        val pseudo: String,
        val password: String
    )

    data class AuthResponse(
        @SerializedName("access_token")
        val accessToken: String,
        val user: UserResponse
    )

    data class UserResponse(
        val id: String,
        val pseudo: String,
        val email: String,
        val createdAt: String,
        val updatedAt: String,
        val watchlist: List<Int>,
        val likes: List<Int>,
        val watched: List<Int>
    )

    fun register(pseudo: String, email: String, password: String): Result<AuthResponse> {
        return try {
            val connection = URL("${baseUrl}/auth/register").openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Accept", "application/json")
            connection.doOutput = true

            val requestBody = gson.toJson(RegisterRequest(pseudo, email, password))
            connection.outputStream.use { it.write(requestBody.toByteArray()) }

            if (connection.responseCode == HttpURLConnection.HTTP_CREATED) {
                val json = connection.inputStream.bufferedReader().use { it.readText() }
                val response = gson.fromJson(json, AuthResponse::class.java)
                Result.success(response)
            } else {
                val errorJson = connection.errorStream?.bufferedReader()?.use { it.readText() }
                Result.failure(Exception("Registration failed: ${connection.responseCode} - $errorJson"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    fun login(pseudo: String, password: String): Result<AuthResponse> {
        return try {
            val connection = URL("${baseUrl}/auth/login").openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Accept", "application/json")
            connection.doOutput = true

            val requestBody = gson.toJson(LoginRequest(pseudo, password))
            connection.outputStream.use { it.write(requestBody.toByteArray()) }

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val json = connection.inputStream.bufferedReader().use { it.readText() }
                val response = gson.fromJson(json, AuthResponse::class.java)
                Result.success(response)
            } else {
                val errorJson = connection.errorStream?.bufferedReader()?.use { it.readText() }
                Result.failure(Exception("Login failed: ${connection.responseCode} - $errorJson"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    fun getProfile(token: String): Result<UserResponse> {
        return try {
            val connection = URL("${baseUrl}/users/profile").openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Authorization", "Bearer $token")
            connection.setRequestProperty("Accept", "application/json")

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val json = connection.inputStream.bufferedReader().use { it.readText() }
                val response = gson.fromJson(json, UserResponse::class.java)
                Result.success(response)
            } else {
                val errorJson = connection.errorStream?.bufferedReader()?.use { it.readText() }
                Result.failure(Exception("Get profile failed: ${connection.responseCode} - $errorJson"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    fun addToWatchlist(token: String, filmId: Int): Result<UserResponse> {
        return try {
            Log.d("MockUserRepository", "addToWatchlist: filmId=$filmId")
            val connection = URL("${baseUrl}/users/watchlist").openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Authorization", "Bearer $token")
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Accept", "application/json")
            connection.doOutput = true

            val requestBody = gson.toJson(mapOf("filmId" to filmId))
            connection.outputStream.use { it.write(requestBody.toByteArray()) }

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val json = connection.inputStream.bufferedReader().use { it.readText() }
                Log.d("MockUserRepository", "addToWatchlist response: $json")
                val response = gson.fromJson(json, UserResponse::class.java)
                Log.d("MockUserRepository", "addToWatchlist parsed: watchlist=${response.watchlist}")
                Result.success(response)
            } else {
                val errorJson = connection.errorStream?.bufferedReader()?.use { it.readText() }
                Log.e("MockUserRepository", "addToWatchlist failed: ${connection.responseCode} - $errorJson")
                Result.failure(Exception("Add to watchlist failed: ${connection.responseCode} - $errorJson"))
            }
        } catch (e: Exception) {
            Log.e("MockUserRepository", "addToWatchlist exception", e)
            e.printStackTrace()
            Result.failure(e)
        }
    }

    fun removeFromWatchlist(token: String, filmId: Int): Result<UserResponse> {
        return try {
            val connection = URL("${baseUrl}/users/watchlist/$filmId").openConnection() as HttpURLConnection
            connection.requestMethod = "DELETE"
            connection.setRequestProperty("Authorization", "Bearer $token")
            connection.setRequestProperty("Accept", "application/json")

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val json = connection.inputStream.bufferedReader().use { it.readText() }
                val response = gson.fromJson(json, UserResponse::class.java)
                Result.success(response)
            } else {
                val errorJson = connection.errorStream?.bufferedReader()?.use { it.readText() }
                Result.failure(Exception("Remove from watchlist failed: ${connection.responseCode} - $errorJson"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    fun addToLikes(token: String, filmId: Int): Result<UserResponse> {
        return try {
            Log.d("MockUserRepository", "addToLikes: filmId=$filmId")
            val connection = URL("${baseUrl}/users/likes").openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Authorization", "Bearer $token")
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Accept", "application/json")
            connection.doOutput = true

            val requestBody = gson.toJson(mapOf("filmId" to filmId))
            connection.outputStream.use { it.write(requestBody.toByteArray()) }

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val json = connection.inputStream.bufferedReader().use { it.readText() }
                Log.d("MockUserRepository", "addToLikes response: $json")
                val response = gson.fromJson(json, UserResponse::class.java)
                Log.d("MockUserRepository", "addToLikes parsed: likes=${response.likes}")
                Result.success(response)
            } else {
                val errorJson = connection.errorStream?.bufferedReader()?.use { it.readText() }
                Log.e("MockUserRepository", "addToLikes failed: ${connection.responseCode} - $errorJson")
                Result.failure(Exception("Add to likes failed: ${connection.responseCode} - $errorJson"))
            }
        } catch (e: Exception) {
            Log.e("MockUserRepository", "addToLikes exception", e)
            e.printStackTrace()
            Result.failure(e)
        }
    }

    fun removeFromLikes(token: String, filmId: Int): Result<UserResponse> {
        return try {
            val connection = URL("${baseUrl}/users/likes/$filmId").openConnection() as HttpURLConnection
            connection.requestMethod = "DELETE"
            connection.setRequestProperty("Authorization", "Bearer $token")
            connection.setRequestProperty("Accept", "application/json")

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val json = connection.inputStream.bufferedReader().use { it.readText() }
                val response = gson.fromJson(json, UserResponse::class.java)
                Result.success(response)
            } else {
                val errorJson = connection.errorStream?.bufferedReader()?.use { it.readText() }
                Result.failure(Exception("Remove from likes failed: ${connection.responseCode} - $errorJson"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    suspend fun addToWatched(token: String, filmId: Int): Result<UserResponse> {
        return try {
            Log.d("MockUserRepository", "addToWatched: filmId=$filmId")
            val connection = URL("${baseUrl}/users/watched").openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Authorization", "Bearer $token")
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Accept", "application/json")
            connection.doOutput = true

            val requestBody = gson.toJson(mapOf("filmId" to filmId))
            connection.outputStream.use { it.write(requestBody.toByteArray()) }

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val json = connection.inputStream.bufferedReader().use { it.readText() }
                Log.d("MockUserRepository", "addToWatched response: $json")
                val response = gson.fromJson(json, UserResponse::class.java)
                Log.d("MockUserRepository", "addToWatched parsed: watched=${response.watched}")
                Result.success(response)
            } else {
                val errorJson = connection.errorStream?.bufferedReader()?.use { it.readText() }
                Log.e("MockUserRepository", "addToWatched failed: ${connection.responseCode} - $errorJson")
                Result.failure(Exception("Add to watched failed: ${connection.responseCode} - $errorJson"))
            }
        } catch (e: Exception) {
            Log.e("MockUserRepository", "addToWatched exception", e)
            e.printStackTrace()
            Result.failure(e)
        }
    }

    suspend fun removeFromWatched(token: String, filmId: Int): Result<UserResponse> {
        return try {
            val connection = URL("${baseUrl}/users/watched/$filmId").openConnection() as HttpURLConnection
            connection.requestMethod = "DELETE"
            connection.setRequestProperty("Authorization", "Bearer $token")
            connection.setRequestProperty("Accept", "application/json")

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val json = connection.inputStream.bufferedReader().use { it.readText() }
                val response = gson.fromJson(json, UserResponse::class.java)
                Result.success(response)
            } else {
                val errorJson = connection.errorStream?.bufferedReader()?.use { it.readText() }
                Result.failure(Exception("Remove from watched failed: ${connection.responseCode} - $errorJson"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}
