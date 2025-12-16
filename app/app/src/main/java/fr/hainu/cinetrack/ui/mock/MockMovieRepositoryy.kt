package fr.hainu.cinetrack.ui.mock

import com.google.gson.Gson
import fr.hainu.cinetrack.BuildConfig
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.domain.models.CastMemberModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class MockMovieRepositoryy {

    private val gson = Gson()
    private val apiKey = BuildConfig.TMDB_API_KEY
    private val baseUrl = BuildConfig.TMDB_BASE_URL

    suspend fun getTrendingMoviesWeek(): List<MovieModel> = withContext(Dispatchers.IO) {
        try {
            val connection = URL("${baseUrl}trending/movie/week?api_key=$apiKey&language=fr-FR").openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Accept", "application/json")

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val json = connection.inputStream.bufferedReader().use { it.readText() }
                val jsonObject = gson.fromJson(json, com.google.gson.JsonObject::class.java)
                val results = jsonObject.getAsJsonArray("results")

                results.mapNotNull { movieElement ->
                    val trendingMovie = movieElement.asJsonObject
                    val movieId = trendingMovie.get("id").asInt
                    val detailsConnection = URL("${baseUrl}movie/$movieId?api_key=$apiKey&language=fr-FR&append_to_response=videos,credits").openConnection() as HttpURLConnection
                    detailsConnection.requestMethod = "GET"
                    detailsConnection.setRequestProperty("Accept", "application/json")

                    if (detailsConnection.responseCode == HttpURLConnection.HTTP_OK) {
                        val detailsJson = detailsConnection.inputStream.bufferedReader().use { it.readText() }
                        val movie = gson.fromJson(detailsJson, com.google.gson.JsonObject::class.java)

                        val genres = movie.getAsJsonArray("genres")
                        val genresString = genres.joinToString(", ") { it.asJsonObject.get("name").asString }

                        val runtime = movie.get("runtime")?.asInt
                        val durationString = runtime?.let { "${it / 60}h ${it % 60}min" } ?: ""

                        val videos = movie.getAsJsonObject("videos")?.getAsJsonArray("results")
                        val trailerKey = videos?.firstOrNull { video ->
                            val v = video.asJsonObject
                            v.get("site").asString == "YouTube" && v.get("type").asString == "Trailer"
                        }?.asJsonObject?.get("key")?.asString
                        val trailerUrl = trailerKey?.let { "https://www.youtube.com/watch?v=$it" } ?: ""

                        val credits = movie.getAsJsonObject("credits")?.getAsJsonArray("cast")
                        val castList = credits?.take(10)?.map { actor ->
                            val a = actor.asJsonObject
                            CastMemberModel(
                                name = a.get("name").asString,
                                profilePictureUrl = a.get("profile_path")?.asString?.let { "https://image.tmdb.org/t/p/w185$it" } ?: ""
                            )
                        } ?: emptyList()

                        MovieModel(
                            id = movie.get("id").asInt,
                            title = movie.get("title").asString,
                            rating = movie.get("vote_average").asDouble,
                            posterUrl = movie.get("poster_path")?.asString?.let { "https://image.tmdb.org/t/p/w500$it" } ?: "",
                            year = movie.get("release_date").asString.take(4),
                            genres = genresString,
                            ratingCoef = movie.get("vote_count").asInt,
                            duration = durationString,
                            synopsis = movie.get("overview").asString,
                            trailerUrl = trailerUrl,
                            cast = castList,
                            reviews = emptyList(),
                            isOnFavorite = false,
                            isOnWatchlist = false,
                            isOnWatched = false,
                            isRated = false
                        )
                    } else {
                        null
                    }
                }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}