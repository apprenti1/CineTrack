package fr.hainu.cinetrack.ui.mock

import com.google.gson.Gson
import com.google.gson.JsonObject
import fr.hainu.cinetrack.BuildConfig
import fr.hainu.cinetrack.domain.models.MovieModel
import java.net.HttpURLConnection
import java.net.URL

class MockMovieRepository {

    private val gson = Gson()
    private val apiKey = BuildConfig.TMDB_API_KEY
    private val baseUrl = BuildConfig.TMDB_BASE_URL
    private val imageUrl = BuildConfig.TMDB_IMAGE_URL
    private val posterSize = BuildConfig.TMDB_IMAGE_POSTERSIZE
    private val backdropSize = BuildConfig.TMDB_IMAGE_BACKDROPSIZE

    suspend fun getTrendingMoviesWeek(): List<MovieModel> {
        try {
            val connection = URL("${baseUrl}trending/movie/week?api_key=$apiKey&language=fr-FR").openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Accept", "application/json")

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val json = connection.inputStream.bufferedReader().use { it.readText() }
                val jsonObject = gson.fromJson(json, JsonObject::class.java)
                val results = jsonObject.getAsJsonArray("results")
                val movies: MutableList<MovieModel> = emptyList<MovieModel>().toMutableList()
                results.forEach { movieElement ->
                    val trendingMovie = movieElement.asJsonObject
                    movies.add(
                        MovieModel(
                            id = trendingMovie.get("id").asInt,
                            title = trendingMovie.get("title").asString,
                            rating = trendingMovie.get("vote_average").asDouble,
                            posterUrl = if (trendingMovie.has("poster_path") && !trendingMovie.get("poster_path").isJsonNull) {
                                "${imageUrl}${posterSize}${trendingMovie.get("poster_path").asString}"
                            } else {
                                ""
                            },
                            backdropUrl = if (trendingMovie.has("backdrop_path") && !trendingMovie.get("backdrop_path").isJsonNull) {
                                "${imageUrl}${backdropSize}${trendingMovie.get("backdrop_path").asString}"
                            } else {
                                ""
                            },
                            year = if (trendingMovie.has("release_date") && !trendingMovie.get("release_date").isJsonNull) {
                                trendingMovie.get("release_date").asString.substringBefore("-")
                            } else {
                                ""
                            },
                            genres = if (trendingMovie.has("genre_ids")) {
                                trendingMovie.getAsJsonArray("genre_ids").joinToString(", ") { it.asInt.toString() }
                            } else {
                                ""
                            },
                            ratingCoef = trendingMovie.get("vote_count").asInt,
                            synopsis = if (trendingMovie.has("overview") && !trendingMovie.get("overview").isJsonNull) {
                                trendingMovie.get("overview").asString
                            } else {
                                ""
                            },
                        )
                    )
                }

                return movies


            } else {
                Exception("Echec de récupération").printStackTrace()
                return emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }
}